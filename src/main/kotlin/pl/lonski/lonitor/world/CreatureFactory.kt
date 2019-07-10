package pl.lonski.lonitor.world

import org.hexworks.zircon.api.TileColors
import org.hexworks.zircon.api.color.ANSITileColor.WHITE

class CreatureFactory(private val world: World) {

    fun newPlayer(): Creature {
        val c = Creature('@', WHITE, world, 100, 20, 5)
        c.setAi(PlayerAi(c))
        world.putAtEmptyLocation(c)
        return c
    }

    fun newFungus(): Creature {
        val color = TileColors.create(128, 128, 9)
        val c = Creature('f', color, world, 10, 0, 0)
        c.setAi(FungusAi(c, this))
        world.putAtEmptyLocation(c)
        return c
    }
}
