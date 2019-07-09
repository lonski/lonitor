package pl.lonski.lonitor.screen

import org.hexworks.zircon.api.Positions
import org.hexworks.zircon.api.Tiles
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.uievent.KeyCode.*
import org.hexworks.zircon.api.uievent.KeyboardEvent
import pl.lonski.lonitor.Point
import pl.lonski.lonitor.world.Creature
import pl.lonski.lonitor.world.CreatureFactory
import pl.lonski.lonitor.world.World
import pl.lonski.lonitor.world.WorldBuilder
import kotlin.math.max
import kotlin.math.min

class PlayScreen : GameScreen {

    private val screenWidth: Int = 80
    private val screenHeight: Int = 43
    private val world = createWorld()
    private val creatureFactory = CreatureFactory(world)
    private val player: Creature = creatureFactory.newPlayer()

    init {
        repeat(5) { creatureFactory.newFungus() }
    }

    override fun display(terminal: TileGrid) {
        world.update()
        val left = getScrollX()
        val top = getScrollY()
        for (x in 0 until screenWidth) {
            for (y in 0 until screenHeight) {
                val worldPos = Point(x + left, y + top)
                terminal.setTileAt(
                    Positions.create(x, y),
                    Tiles.newBuilder()
                        .withForegroundColor(world.color(worldPos))
                        .withCharacter(world.glyph(worldPos))
                        .build()
                )
            }
        }
    }

    override fun handleInput(event: KeyboardEvent): GameScreen? {
        when (event.code) {
            KEY_H, LEFT, NUMPAD_4 -> player.moveBy(-1, 0)
            KEY_L, RIGHT, NUMPAD_6 -> player.moveBy(1, 0)
            KEY_K, UP, NUMPAD_8 -> player.moveBy(0, -1)
            KEY_J, DOWN, NUMPAD_2 -> player.moveBy(0, 1)
            KEY_Y, NUMPAD_7 -> player.moveBy(-1, -1)
            KEY_U, NUMPAD_9 -> player.moveBy(1, -1)
            KEY_B, NUMPAD_1 -> player.moveBy(-1, 1)
            KEY_N, NUMPAD_3 -> player.moveBy(1, 1)
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
        return WorldBuilder(80, 43)
            .usingTravellerGenerator()
            .build()
    }
}


