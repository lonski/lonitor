package pl.lonski.lonitor.world

import pl.lonski.lonitor.Point
import java.awt.Color

class World(private var tiles: Array<Array<Tile>>) {

    val width = tiles.size
    val height = tiles[0].size
    private val creatures = ArrayList<Creature>()

    fun glyph(pos: Point): Char {
        return creature(pos)?.glyph() ?: tiles[pos.x][pos.y].glyph
    }

    fun color(pos: Point): Color {
        return creature(pos)?.color() ?: tiles[pos.x][pos.y].color
    }

    fun tile(pos: Point): Tile {
        if (pos.x < 0 || pos.x >= width || pos.y < 0 || pos.y >= height)
            return Tile.BOUNDS

        return tiles[pos.x][pos.y]
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

    fun putAtEmptyLocation(creature: Creature) {
        creatures.add(creature)
        creature.setPosition(getEmptyLocation())
    }

    private fun getEmptyLocation(): Point {
        var pos: Point
        do {
            pos = Point((Math.random() * width).toInt(), (Math.random() * height).toInt())
        } while (!tile(pos).isGroud())

        return pos
    }
}
