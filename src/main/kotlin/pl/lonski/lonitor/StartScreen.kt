package pl.lonski.lonitor

import org.hexworks.zircon.api.color.ANSITileColor.MAGENTA
import org.hexworks.zircon.api.color.ANSITileColor.WHITE
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.input.KeyStroke

class StartScreen : GameScreen {

    override fun display(terminal: TileGrid) {
        drawText("Lonitor", Position.offset1x1(), MAGENTA, terminal)
        drawText("[Press enter to start]", Position.create(1, 3), WHITE, terminal)
    }

    override fun handleInput(key: KeyStroke): GameScreen? {
        return if (key.getCharacter() == '\n') PlayScreen() else null
    }
}
