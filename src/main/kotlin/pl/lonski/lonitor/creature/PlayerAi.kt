package pl.lonski.lonitor.creature

import pl.lonski.lonitor.Point
import pl.lonski.lonitor.world.Fov
import pl.lonski.lonitor.world.Tile

class PlayerAi(
    creature: Creature,
    private val messages: MutableList<String>,
    private val fov: Fov
) : CreatureAi(creature) {

    override fun onEnter(pos: Point, tile: Tile) {
        if (tile.isGround())
            creature.setPosition(pos)
    }

    override fun onNotify(message: String) {
        messages.add(message)
    }

    override fun canSee(pos: Point): Boolean = fov.isVisible(pos)

    override fun onUpdate() {
        fov.update(creature.position(), creature.visionRadius())
    }
}
