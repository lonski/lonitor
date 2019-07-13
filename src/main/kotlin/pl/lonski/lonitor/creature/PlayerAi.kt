package pl.lonski.lonitor.creature

import pl.lonski.lonitor.Point
import pl.lonski.lonitor.world.Tile

class PlayerAi(creature: Creature, private val messages: MutableList<String>) : CreatureAi(creature) {

    override fun onEnter(pos: Point, tile: Tile) {
        if (tile.isGround()) creature.setPosition(pos)
    }

    override fun onNotify(message: String) {
        messages.add(message)
    }
}
