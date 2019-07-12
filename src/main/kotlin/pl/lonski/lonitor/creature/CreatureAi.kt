package pl.lonski.lonitor.creature

import pl.lonski.lonitor.Point
import pl.lonski.lonitor.world.Tile

open class CreatureAi(protected val creature: Creature) {

    open fun onEnter(pos: Point, tile: Tile) {}
    open fun onUpdate() {}
    open fun onNotify(message: String) {}
}
