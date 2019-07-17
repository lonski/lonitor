package pl.lonski.lonitor.creature

import pl.lonski.lonitor.world.Fov
import pl.lonski.lonitor.world.World
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
            ',', Color.ORANGE, world, "fungus", 10, 0, 0
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
