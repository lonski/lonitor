package pl.lonski.lonitor.world

import org.hexworks.zircon.api.TileColors
import org.hexworks.zircon.api.color.ANSITileColor.WHITE

class CreatureFactory(private val world: World) {

    fun newPlayer(): Creature {
        val c = Creature('@', WHITE, world)
        c.setAi(PlayerAi(c))
        world.putAtEmptyLocation(c)
        return c
    }

    fun newFungus(): Creature {
        val c = Creature('f', TileColors.create(128, 128, 9), world)
        c.setAi(FungusAi(c, this))
        world.putAtEmptyLocation(c)
        return c
    }
}
