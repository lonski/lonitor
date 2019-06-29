package pl.lonski.lonitor

import org.hexworks.zircon.api.Positions
import org.hexworks.zircon.api.Tiles
import org.hexworks.zircon.api.color.ANSITileColor
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.input.InputType.*
import org.hexworks.zircon.api.input.KeyStroke
import pl.lonski.lonitor.Tile.FLOOR
import pl.lonski.lonitor.Tile.WALL
import kotlin.math.max
import kotlin.math.min

class PlayScreen : GameScreen {

    val world: World = createWorld()
    val screenWidth: Int = 80
    val screenHeight: Int = 43
    var centerX: Int = 0
    var centerY: Int = 0

    override fun display(terminal: TileGrid) {
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

    override fun handleInput(key: KeyStroke): GameScreen? {
        if (key.getCharacter() == 'h' || key.inputTypeIs(ArrowLeft) || key.inputTypeIs(Numpad4)) {
            scrollBy(-1, 0)
        } else if (key.getCharacter() == 'l' || key.inputTypeIs(ArrowRight) || key.inputTypeIs(Numpad6)) {
            scrollBy(1, 0)
        } else if (key.getCharacter() == 'k' || key.inputTypeIs(ArrowUp) || key.inputTypeIs(Numpad8)) {
            scrollBy(0, -1)
        } else if (key.getCharacter() == 'j' || key.inputTypeIs(ArrowDown) || key.inputTypeIs(Numpad2)) {
            scrollBy(0, 1)
        } else if (key.getCharacter() == 'y' || key.inputTypeIs(Numpad7)) {
            scrollBy(-1, -1)
        } else if (key.getCharacter() == 'u' || key.inputTypeIs(Numpad9)) {
            scrollBy(1, -1)
        } else if (key.getCharacter() == 'b' || key.inputTypeIs(Numpad1)) {
            scrollBy(-1, 1)
        } else if (key.getCharacter() == 'n' || key.inputTypeIs(Numpad3)) {
            scrollBy(1, 1)
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
        return WorldBuilder(100, 100)
            .build()
    }
}

enum class Tile(val glyph: Char, val color: ANSITileColor) {

    FLOOR('.', ANSITileColor.GRAY),
    WALL('#', ANSITileColor.GRAY)
}

class World(var tiles: Array<Array<Tile>>) {

    val width = tiles.size
    val height = tiles[0].size

    fun glyph(x: Int, y: Int): Char {
        return tiles[x][y].glyph
    }

    fun color(x: Int, y: Int): ANSITileColor {
        return tiles[x][y].color
    }
}

class WorldBuilder(val width: Int, val height: Int) {

    fun build(): World {
        return World(randomTiles())
    }

    private fun randomTiles(): Array<Array<Tile>> {
        return Array(width) { Array(height) { if (Math.random() > 0.5) WALL else FLOOR } }
    }
}

