package pl.lonski.lonitor.world

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import pl.lonski.lonitor.Point

class WorldTest {

    @Test
    fun testIsInBounds() {
        val depth = 1
        val width = 10
        val height = 20
        val world = World(Array(depth) { Array(width) { Array(height) { Tile.WALL } } })

        assertTrue(world.isInBounds(Point(0, 0, 0)))
        assertTrue(world.isInBounds(Point(width - 1, height - 1, depth - 1)))
        assertFalse(world.isInBounds(Point(0, 0, depth)))
        assertFalse(world.isInBounds(Point(0, height, 0)))
        assertFalse(world.isInBounds(Point(width, 0, 0)))
    }

}
