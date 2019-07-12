package pl.lonski.lonitor.world

import java.awt.Color


class CreatureFactory(private val world: World) {

    fun newPlayer(messages: MutableList<String>): Creature {
        val c = Creature('@', Color.WHITE, world, "player", 100, 20, 5)
        c.setAi(PlayerAi(c, messages))
        world.putAtEmptyLocation(c)
        return c
    }

    fun newFungus(): Creature {
        val color = Color(128, 128, 9)
        val c = Creature('f', color, world, "fungus", 10, 0, 0)
        c.setAi(FungusAi(c, this))
        world.putAtEmptyLocation(c)
        return c
    }
}
