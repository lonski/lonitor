package pl.lonski.lonitor.world

import pl.lonski.lonitor.creature.BatAi
import pl.lonski.lonitor.creature.Creature
import pl.lonski.lonitor.creature.FungusAi
import pl.lonski.lonitor.creature.PlayerAi
import java.awt.Color


class CreatureFactory(private val world: World) {

    fun newPlayer(messages: MutableList<String>, fov: Fov): Creature {
        val creature = Creature(
            '@', Color.WHITE, world, "player", 100, 20, 5
        )
        creature.setAi(PlayerAi(creature, messages, fov))
        world.putAtEmptyLocation(creature, 0)
        return creature
    }

    fun newFungus(depth: Int): Creature {
        val creature = Creature(
            'f', Color.ORANGE, world, "fungus", 10, 0, 0
        )
        creature.setAi(FungusAi(creature, this))
        world.putAtEmptyLocation(creature, depth)
        return creature
    }

    fun newBat(depth: Int): Creature {
        val creature = Creature(
            'b', Color(175, 95, 0), world, "bat", 15, 5, 0
        )
        creature.setAi(BatAi(creature))
        world.putAtEmptyLocation(creature, depth)
        return creature
    }
}
