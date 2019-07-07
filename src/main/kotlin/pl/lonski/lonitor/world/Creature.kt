package pl.lonski.lonitor.world

import org.hexworks.zircon.api.color.ANSITileColor
import pl.lonski.lonitor.Point

class Creature(
    private val glyph: Char,
    private val color: ANSITileColor,
    private val world: World
) {
    private var ai: CreatureAi = CreatureAi(this)
    private var pos: Point = Point(0, 0)

    fun glyph(): Char = glyph
    fun color(): ANSITileColor = color
    fun position(): Point = pos

    fun setPosition(pos: Point) {
        this.pos = pos
    }

    fun moveBy(mx: Int, my: Int) {
        val dest = Point(pos.x + mx, pos.y + my)
        ai.onEnter(dest, world.tile(dest))
    }
}

class CreatureAi(private val creature: Creature) {

    fun onEnter(pos: Point, tile: Tile) {
        if (tile.isGroud()) {
            creature.setPosition(pos)
        }
    }
}

