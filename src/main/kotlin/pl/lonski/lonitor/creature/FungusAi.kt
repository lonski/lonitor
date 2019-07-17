package pl.lonski.lonitor.creature

import pl.lonski.lonitor.Point
import kotlin.random.Random

class FungusAi(creature: Creature, private val creatureFactory: CreatureFactory) : CreatureAi(creature) {

    private var spreadCount = 0

    override fun onUpdate() {
        if (spreadCount < 1 && Math.random() < 0.02) spread()
    }

    private fun spread() {
        val dest = Point(
            creature.position().x + Random.nextInt(-1, 2),
            creature.position().y + Random.nextInt(-1, 2),
            creature.position().z
        )
        if (creature.canEnter(dest)) {
            creatureFactory.newFungus(creature.position().z).setPosition(dest)
            spreadCount++
            creature.doAction("spawn a child")
        }
    }
}
