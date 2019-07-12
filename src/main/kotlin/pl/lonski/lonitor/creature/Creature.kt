package pl.lonski.lonitor.creature

import pl.lonski.lonitor.Point
import pl.lonski.lonitor.world.World
import java.awt.Color
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class Creature(
    private val glyph: Char,
    private val color: Color,
    private val world: World,
    private val name: String,
    private var maxHp: Int,
    private var attackValue: Int,
    private var defenseValue: Int
) {
    private var hp: Int = maxHp
    private var ai: CreatureAi = CreatureAi(this)
    private var pos: Point = Point(0, 0, 0)

    fun glyph(): Char = glyph
    fun color(): Color = color
    fun position(): Point = pos
    fun hp(): Int = hp
    fun maxHp(): Int = maxHp
    fun attackValue(): Int = attackValue
    fun defenseValue(): Int = defenseValue

    fun setPosition(pos: Point) {
        this.pos = pos
    }

    fun setAi(ai: CreatureAi) {
        this.ai = ai
    }

    fun canEnter(pos: Point): Boolean = world.tile(pos).isGroud() && world.creature(pos) == null

    fun update() {
        ai.onUpdate()
    }

    fun notify(message: String) {
        ai.onNotify(message)
    }

    fun moveBy(mx: Int, my: Int, mz: Int) {
        val dest = Point(pos.x + mx, pos.y + my, pos.z + mz)
        val creature = world.creature(dest)
        if (creature == null) ai.onEnter(dest, world.tile(dest)) else attack(creature)
    }

    fun attack(creature: Creature) {
        val damage: Int = Random.nextInt(1, max(1, attackValue() - creature.defenseValue()))
        creature.modifyHp(-damage)
        doAction("attack ${creature.name} for $damage damage")
    }

    fun modifyHp(amount: Int) {
        hp = min(maxHp, hp + amount)
        if (hp <= 0) {
            doAction("die")
            world.remove(this)
        }
    }

    fun doAction(action: String) {
        inRadiusOf(9) { (x, y, z) ->
            val point = Point(pos.x + x, pos.y + y, pos.z + z)
            val creature = world.creature(point)
            if (creature != null) if (creature == this)
                creature.notify("You $action.")
            else
                creature.notify("The $name ${makeSecondPerson(action)}.")
        }
    }

    fun inRadiusOf(r: Int, f: (Point) -> Unit) {
        for (ox in -r..r) for (oy in -r..r) {
            if (ox * ox + oy * oy > r * r) continue
            f(Point(ox, oy, 0))
        }
    }

    private fun makeSecondPerson(action: String): String {
        val idx = action.indexOf(' ')
        return if (idx > 0) action.replaceFirst(" ", "s ") else action + "s"
    }
}

