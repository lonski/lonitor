package pl.lonski.lonitor.world

import pl.lonski.lonitor.Point
import pl.lonski.lonitor.creature.Creature
import pl.lonski.lonitor.item.Item
import java.awt.Color
import kotlin.random.Random

class World(private var tiles: Array<Array<Array<Tile>>>) {

    val width = tiles[0].size
    val height = tiles[0][0].size
    val depth = tiles.size
    private val creatures = ArrayList<Creature>()
    private val items: Array<Array<Array<Item?>>> = Array(depth) { Array(width) { arrayOfNulls<Item>(height) } }

    fun glyph(pos: Point): Char {
        return creature(pos)?.glyph() ?: item(pos)?.glyph() ?: tiles[pos.z][pos.x][pos.y].glyph
    }

    fun color(pos: Point): Color {
        return creature(pos)?.color() ?: item(pos)?.color() ?: tiles[pos.z][pos.x][pos.y].color
    }

    fun tile(pos: Point): Tile {
        if (pos.x < 0 || pos.x >= width || pos.y < 0 || pos.y >= height || pos.z < 0 || pos.z >= depth)
            return Tile.BOUNDS

        return tiles[pos.z][pos.x][pos.y]
    }

    fun creature(pos: Point): Creature? {
        return creatures.find { it.position() == pos }
    }

    fun item(pos: Point): Item? {
        return items[pos.z][pos.x][pos.y]
    }

    fun update() {
        val toUpdate = ArrayList(creatures)
        toUpdate.forEach { it.update() }
    }

    fun remove(creature: Creature) {
        creatures.remove(creature)
    }

    fun putAtEmptyLocation(creature: Creature, depth: Int) {
        var p: Point
        do {
            p = Point(Random.nextInt(width), Random.nextInt(height), depth)
        } while (!tile(p).isGround())

        creatures.add(creature)
        creature.setPosition(p)
    }

    fun putAtEmptyLocation(item: Item, depth: Int) {
        var p: Point
        do {
            p = Point(Random.nextInt(width), Random.nextInt(height), depth)
        } while (!tile(p).isGround() || item(p) != null)
        items[p.z][p.x][p.y] = item
    }

    fun isInBounds(pos: Point): Boolean =
        pos.x in 0 until width && pos.y in 0 until height && pos.z in 0 until depth
}
