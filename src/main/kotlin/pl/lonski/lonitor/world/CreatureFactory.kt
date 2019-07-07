package pl.lonski.lonitor.world

import org.hexworks.zircon.api.color.ANSITileColor

class CreatureFactory(private val world: World) {

    fun newPlayer(): Creature {
        return Creature('@', ANSITileColor.WHITE, world)
    }
}
