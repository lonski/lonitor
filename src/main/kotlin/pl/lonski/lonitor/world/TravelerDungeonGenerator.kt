package pl.lonski.lonitor.world

import pl.lonski.lonitor.Line
import pl.lonski.lonitor.Point
import kotlin.math.max

class TravelerDungeonGenerator(
    private val width: Int,
    private val height: Int,
    private val fillLevel: Float
) : DungeonGenerator {
    private val directions: List<Point> = Point.getDirections4()
    private val sizeMin = 3
    private val sizeMax = 8
    private val iterationBatchSize = 50

    private var tiles = Array(width) { Array(height) { Tile.WALL } }
    private var pos = getRandomPoint()
    private var lastDirection = Point(0, 0, 0)

    override fun generate(): Array<Array<Tile>> {
        do {
            (0..iterationBatchSize).forEach { _ -> nextIteration() }
        } while (calcFillLevel() < fillLevel)

        return tiles
    }

    private fun calcFillLevel(): Float {
        val floors = tiles.sumBy { it.count { tile -> tile == Tile.FLOOR } }
        return floors.toFloat() / (width * height - floors)
    }

    private fun nextIteration(): Array<Array<Tile>> {
        var direction = directions.random()
        while (isOppositeDirection(direction))
            direction = directions.random()

        if (!tryMakeRoom(direction))
            tryMakeCorridor(direction) || tryMakeCorridor(direction) || tryMakeCorridor(direction)

        lastDirection = direction

        return tiles
    }

    private fun isOppositeDirection(direction: Point): Boolean {
        if (lastDirection.x == 0 && lastDirection.y == 0)
            return false

        return direction.x == -lastDirection.x || direction.y == -lastDirection.y
    }

    private fun tryMakeCorridor(direction: Point): Boolean {
        val size = randomSize()
        val end = Point(pos.x + direction.x * size, pos.y + direction.y * size, 0)
        if (isWithinBorders(end)) {
            Line(pos, end).points.forEach { tiles[it.x][it.y] = Tile.FLOOR }
            pos = end
            return true
        }
        return false
    }

    private fun tryMakeRoom(direction: Point): Boolean {
        val points = ArrayList<Point>()
        val start = Point(pos.x + direction.x, pos.y + direction.y, 0)
        val end = Point(start.x + randomSize(), start.y + randomSize(), 0)

        if (!isWithinBorders(end)) {
            return false
        }

        for (x in start.x..end.x) {
            for (y in start.y..end.y) {
                if (tiles[x][y] != Tile.WALL)
                    return false
                points.add(Point(x, y, 0))
            }
        }

        if (points.isEmpty())
            return false

        points.forEach { tiles[it.x][it.y] = Tile.FLOOR }
        pos = points.random()

        return true
    }

    private fun randomSize() = sizeMin + (Math.random() * sizeMax).toInt()

    private fun isWithinBorders(point: Point): Boolean =
        point.x > 0 && (point.x < width - 1) && point.y > 0 && (point.y < height - 1)

    private fun getRandomPoint() =
        Point(
            max(2, (Math.random() * width - 2).toInt()),
            max(2, (Math.random() * height - 2).toInt()),
            0
        )
}
