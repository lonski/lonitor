package pl.lonski.lonitor.screen

import asciiPanel.AsciiPanel
import pl.lonski.lonitor.Point
import pl.lonski.lonitor.world.Creature
import pl.lonski.lonitor.world.CreatureFactory
import pl.lonski.lonitor.world.World
import pl.lonski.lonitor.world.WorldBuilder
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import kotlin.math.max
import kotlin.math.min

class PlayScreen : GameScreen {

    private val screenWidth: Int = 80
    private val screenHeight: Int = 24
    private val world = createWorld()
    private val creatureFactory = CreatureFactory(world)
    private val player: Creature = creatureFactory.newPlayer()

    init {
        repeat(5) { creatureFactory.newFungus() }
    }

    override fun display(terminal: AsciiPanel) {
        world.update()
        val left = getScrollX()
        val top = getScrollY()
        for (y in 0 until screenHeight) {
            for (x in 0 until screenWidth) {
                val worldPos = Point(x + left, y + top)
                terminal.write(world.glyph(worldPos), x, y, world.color(worldPos))
            }
        }
    }

    private fun displayStats() {

    }

    override fun handleInput(key: KeyEvent): GameScreen? {
        when (key.keyCode) {
            VK_H, VK_LEFT, VK_NUMPAD4 -> player.moveBy(-1, 0)
            VK_L, VK_RIGHT, VK_NUMPAD6 -> player.moveBy(1, 0)
            VK_K, VK_UP, VK_NUMPAD8 -> player.moveBy(0, -1)
            VK_J, VK_DOWN, VK_NUMPAD2 -> player.moveBy(0, 1)
            VK_Y, VK_NUMPAD7 -> player.moveBy(-1, -1)
            VK_U, VK_NUMPAD9 -> player.moveBy(1, -1)
            VK_B, VK_NUMPAD1 -> player.moveBy(-1, 1)
            VK_N, VK_NUMPAD3 -> player.moveBy(1, 1)
            else -> {
            }
        }

        return null
    }

    private fun getScrollX(): Int {
        return max(0, min(player.position().x - screenWidth / 2, world.width - screenWidth))
    }

    private fun getScrollY(): Int {
        return max(0, min(player.position().y - screenHeight / 2, world.height - screenHeight))
    }

    private fun createWorld(): World {
        return WorldBuilder(100, 50)
            .usingTravellerGenerator()
            .build()
    }
}


