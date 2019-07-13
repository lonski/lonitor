package pl.lonski.lonitor.creature

import pl.lonski.lonitor.Line
import pl.lonski.lonitor.Point
import pl.lonski.lonitor.world.Tile

open class CreatureAi(protected val creature: Creature) {

    open fun onEnter(pos: Point, tile: Tile) {}

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

    private fun inVisionRadius(pos: Point): Boolean {
        val dx = pos.x - creature.position().x
        val dy = pos.y - creature.position().y
        return dx * dx + dy * dy <= creature.visionRadius() * creature.visionRadius()
    }
}
