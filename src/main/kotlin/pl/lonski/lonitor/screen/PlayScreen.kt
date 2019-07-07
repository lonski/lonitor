package pl.lonski.lonitor.screen

import org.hexworks.zircon.api.Positions
import org.hexworks.zircon.api.Tiles
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.uievent.KeyCode.*
import org.hexworks.zircon.api.uievent.KeyboardEvent
import pl.lonski.lonitor.world.World
import pl.lonski.lonitor.world.WorldBuilder
import kotlin.math.max
import kotlin.math.min

class PlayScreen : GameScreen {

    private val screenWidth: Int = 80
    private val screenHeight: Int = 43

    var centerX: Int = 0
    var centerY: Int = 0
    private var world = createWorld()

    override fun display(terminal: TileGrid) {
//        world = createWorld()

        val left = getScrollX()
        val top = getScrollY()
        for (x in 0 until screenWidth) {
            for (y in 0 until screenHeight) {
                val wx = x + left
                val wy = y + top

                val glyph = if (wx == centerX && wy == centerY) '@' else world.glyph(wx, wy)
                terminal.setTileAt(
                    Positions.create(x, y),
                    Tiles.newBuilder()
                        .withForegroundColor(world.color(wx, wy))
                        .withCharacter(glyph)
                        .build()
                )
            }
        }

    }

    override fun handleInput(event: KeyboardEvent): GameScreen? {
        when (event.code) {
            KEY_H, LEFT, NUMPAD_4 -> scrollBy(-1, 0)
            KEY_L, RIGHT, NUMPAD_6 -> scrollBy(1, 0)
            KEY_K, UP, NUMPAD_8 -> scrollBy(0, -1)
            KEY_J, DOWN, NUMPAD_2 -> scrollBy(0, 1)
            KEY_Y, NUMPAD_7 -> scrollBy(-1, -1)
            KEY_U, NUMPAD_9 -> scrollBy(1, -1)
            KEY_B, NUMPAD_1 -> scrollBy(-1, 1)
            KEY_N, NUMPAD_3 -> scrollBy(1, 1)
            else -> {
            }
        }

        return null
    }

    private fun getScrollX(): Int {
        return max(0, min(centerX - screenWidth / 2, world.width - screenWidth))
    }

    private fun getScrollY(): Int {
        return max(0, min(centerY - screenHeight / 2, world.height - screenHeight))
    }

    private fun scrollBy(mx: Int, my: Int) {
        centerX = max(0, min(centerX + mx, world.width - 1))
        centerY = max(0, min(centerY + my, world.height - 1))
    }

    private fun createWorld(): World {
        return WorldBuilder(80, 43)
            .usingTravellerGenerator()
            .build()
    }
}


