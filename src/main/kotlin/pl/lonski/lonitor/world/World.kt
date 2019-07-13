package pl.lonski.lonitor.world

import pl.lonski.lonitor.Point
import pl.lonski.lonitor.creature.Creature
import java.awt.Color
import kotlin.random.Random

class World(private var tiles: Array<Array<Array<Tile>>>) {

    val width = tiles[0].size
    val height = tiles[0][0].size
    val depth = tiles.size
    private val creatures = ArrayList<Creature>()

    fun glyph(pos: Point): Char {
        return creature(pos)?.glyph() ?: tiles[pos.z][pos.x][pos.y].glyph
    }

    fun color(pos: Point): Color {
        return creature(pos)?.color() ?: tiles[pos.z][pos.x][pos.y].color
    }

    fun tile(pos: Point): Tile {
        if (pos.x < 0 || pos.x >= width || pos.y < 0 || pos.y >= height || pos.z < 0 || pos.z >= depth)
            return Tile.BOUNDS

        return tiles[pos.z][pos.x][pos.y]
    }

    fun creature(pos: Point): Creature? {
        return creatures.find { it.position() == pos }
    }

    fun update() {
        val toUpdate = ArrayList(creatures)
        toUpdate.forEach { it.update() }
    }

    fun remove(creature: Creature) {
        creatures.remove(creature)
    }

    fun putAtEmptyLocation(creature: Creature, depth: Int) {
        creatures.add(creature)
        creature.setPosition(getEmptyLocation(depth))
    }

    private fun getEmptyLocation(depth: Int): Point {
        var pos: Point
        do {
            pos = Point(Random.nextInt(width), Random.nextInt(height), depth)
        } while (!tile(pos).isGround())

        return pos
    }
}
