package pl.lonski.lonitor.creature

class BatAi(creature: Creature) : CreatureAi(creature) {

    override fun onUpdate() {
        wander()
        wander()
    }
}
