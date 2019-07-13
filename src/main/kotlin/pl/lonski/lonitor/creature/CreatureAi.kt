package pl.lonski.lonitor.creature

import pl.lonski.lonitor.Line
import pl.lonski.lonitor.Point
import pl.lonski.lonitor.world.Tile
import kotlin.random.Random

open class CreatureAi(protected val creature: Creature) {

    open fun onEnter(pos: Point, tile: Tile) {
        if (tile.isGround())
            creature.setPosition(pos)
        else
            creature.doAction("bump into a wall")
    }

    open fun onUpdate() {}

    open fun onNotify(message: String) {}

    open fun canSee(pos: Point): Boolean {
        if (pos == creature.position())
            return true

        if (pos.z != creature.position().z || !inVisionRadius(pos))
            return false

        for (point in Line(pos, creature.position()).points)
            if (!creature.tile(point).isGround())
                return false

        return true
    }

    open fun wander() {
        val mx = Random.nextInt(-1, 2)
        val my = Random.nextInt(-1, 2)
        val dest = Point(creature.position().x + mx, creature.position().y + my, creature.position().z)

        val other = creature.creature(dest)
        if (other != null && other.glyph() == creature.glyph())
            return

        creature.moveBy(mx, my, 0)
    }

    private fun inVisionRadius(pos: Point): Boolean {
        val dx = pos.x - creature.position().x
        val dy = pos.y - creature.position().y
        return dx * dx + dy * dy <= creature.visionRadius() * creature.visionRadius()
    }
}
