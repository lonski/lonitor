package pl.lonski.lonitor.creature

import io.mockk.every
import io.mockk.mockkClass
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import pl.lonski.lonitor.Point
import pl.lonski.lonitor.world.Tile
import pl.lonski.lonitor.world.World
import java.awt.Color

class CreatureTest {

    private val world = mockkClass(World::class)

    @Test
    fun testCanSee() {
        val visionRadius = 4
        val creature = newCreature(visionRadius)

        assertFalse(creature.canSee(Point(0, 0, 1)))
        every { world.tile(any()) } returns Tile.FLOOR
        assertFalse(creature.canSee(Point(visionRadius, 1, 0)))
        assertTrue(creature.canSee(Point(visionRadius, 0, 0)))
        every { world.tile(any()) } returns Tile.WALL
        assertFalse(creature.canSee(Point(1, 0, 0)))
        assertTrue(creature.canSee(Point(0, 0, 0)))
    }

    private fun newCreature(visionRadius: Int): Creature {
        return Creature(
            'a', Color.WHITE, world, "test", 10, 1, 1, visionRadius
        )
    }
}

