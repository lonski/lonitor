package pl.lonski.lonitor.world

import org.hexworks.zircon.api.color.TileColor
import pl.lonski.lonitor.Point
import kotlin.random.Random

class Creature(
    private val glyph: Char,
    private val color: TileColor,
    private val world: World
) {
    private var ai: CreatureAi = CreatureAi(this)
    private var pos: Point = Point(0, 0)

    fun glyph(): Char = glyph
    fun color(): TileColor = color
    fun position(): Point = pos

    fun setPosition(pos: Point) {
        this.pos = pos
    }

    fun setAi(ai: CreatureAi) {
        this.ai = ai
    }

    fun canEnter(pos: Point): Boolean = world.tile(pos).isGroud() && world.creature(pos) == null

    fun update() {
        ai.onUpdate()
    }

    fun moveBy(mx: Int, my: Int) {
        val dest = Point(pos.x + mx, pos.y + my)
        val creature = world.creature(dest)
        if (creature == null) {
            ai.onEnter(dest, world.tile(dest))
        } else {
            attack(creature)
        }
    }

    private fun attack(creature: Creature) {
        world.remove(creature)
    }
}

open class CreatureAi(protected val creature: Creature) {

    open fun onEnter(pos: Point, tile: Tile) {}
    open fun onUpdate() {}
}

class PlayerAi(creature: Creature) : CreatureAi(creature) {

    override fun onEnter(pos: Point, tile: Tile) {
        if (tile.isGroud()) {
            creature.setPosition(pos)
        }
    }
}

class FungusAi(creature: Creature, private val creatureFactory: CreatureFactory) : CreatureAi(creature) {

    private var spreadCount = 0

    override fun onUpdate() {
        if (spreadCount < 1 && Math.random() < 0.02) {
            spread()
        }
    }

    private fun spread() {
        val dest = Point(
            creature.position().x + Random.nextInt(-1, 1),
            creature.position().y + Random.nextInt(-1, 1)
        )
        if (creature.canEnter(dest)) {
            creatureFactory.newFungus().setPosition(dest)
            spreadCount++
        }
    }
}
