package pl.lonski.lonitor.screen

import asciiPanel.AsciiPanel
import pl.lonski.lonitor.Point
import pl.lonski.lonitor.creature.Creature
import pl.lonski.lonitor.creature.CreatureFactory
import pl.lonski.lonitor.item.ItemFactory
import pl.lonski.lonitor.world.Fov
import pl.lonski.lonitor.world.World
import pl.lonski.lonitor.world.WorldBuilder
import java.awt.Color
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class PlayScreen : GameScreen {

    private val screenWidth: Int = 80
    private val screenHeight: Int = 23
    private val world = createWorld()
    private val fov = Fov(world)
    private val messages: MutableList<String> = ArrayList()
    private val creatureFactory = CreatureFactory(world)
    private val itemFactory = ItemFactory(world)
    private val player: Creature = creatureFactory.newPlayer(messages, fov)

    init {
        spawnCreatures()
        spawnItems()
        world.update()
    }

    override fun handleInput(key: KeyEvent): GameScreen? {
        when (key.keyCode) {
            VK_H, VK_LEFT, VK_NUMPAD4 -> player.moveBy(-1, 0, 0)
            VK_L, VK_RIGHT, VK_NUMPAD6 -> player.moveBy(1, 0, 0)
            VK_K, VK_UP, VK_NUMPAD8 -> player.moveBy(0, -1, 0)
            VK_J, VK_DOWN, VK_NUMPAD2 -> player.moveBy(0, 1, 0)
            VK_Y, VK_NUMPAD7 -> player.moveBy(-1, -1, 0)
            VK_U, VK_NUMPAD9 -> player.moveBy(1, -1, 0)
            VK_B, VK_NUMPAD1 -> player.moveBy(-1, 1, 0)
            VK_N, VK_NUMPAD3 -> player.moveBy(1, 1, 0)
            VK_ESCAPE -> return LoseScreen()
        }
        when (key.keyChar) {
            '>' -> player.moveBy(0, 0, 1)
            '<' -> player.moveBy(0, 0, -1)
        }

        world.update()

        return if (player.hp() <= 0) LoseScreen() else null
    }

    override fun display(terminal: AsciiPanel) {
        displayTiles(terminal)
        displayStats(terminal)
        displayMessages(terminal)
    }

    private fun displayTiles(terminal: AsciiPanel) {
        val left = getScrollX()
        val top = getScrollY()
        for (y in 0 until screenHeight) {
            for (x in 0 until screenWidth) {
                val worldPos = Point(x + left, y + top, player.position().z)
                val isInFov = player.canSee(worldPos)
                val color = if (isInFov) world.color(worldPos) else Color.DARK_GRAY
                val glyph = if (isInFov) world.glyph(worldPos) else fov.tile(worldPos).glyph
                terminal.write(glyph, x, y, color)
            }
        }
    }

    private fun displayStats(terminal: AsciiPanel) {
        var stats = "hp: ${player.hp()}/${player.maxHp()}, depth: ${player.position().z + 1}"
        stats += " ".repeat(screenWidth - stats.length)
        terminal.write(stats, 0, screenHeight, Color.LIGHT_GRAY, Color.DARK_GRAY)
    }

    private fun displayMessages(terminal: AsciiPanel) {
        for (i in messages.size - 1 downTo 0) {
            terminal.write(messages[i], 0, messages.size - i - 1)
        }
        messages.clear()
    }

    private fun getScrollX(): Int {
        return max(0, min(player.position().x - screenWidth / 2, world.width - screenWidth))
    }

    private fun getScrollY(): Int {
        return max(0, min(player.position().y - screenHeight / 2, world.height - screenHeight))
    }

    private fun createWorld(): World {
        return WorldBuilder(120, 40)
            .usingTravellerGenerator()
            .levels(3)
            .build()
    }

    private fun spawnCreatures() {
        for (depth in 0 until world.depth) {
            repeat(Random.nextInt(5)) { creatureFactory.newFungus(depth) }
            repeat(Random.nextInt(10)) { creatureFactory.newBat(depth) }
        }
    }

    private fun spawnItems() {
        for (depth in 0 until world.depth) {
            repeat(Random.nextInt(50)) { itemFactory.newRock(depth) }
        }
    }
}
