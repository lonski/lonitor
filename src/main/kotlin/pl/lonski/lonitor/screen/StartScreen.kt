package pl.lonski.lonitor.screen

import org.hexworks.zircon.api.color.ANSITileColor.MAGENTA
import org.hexworks.zircon.api.color.ANSITileColor.WHITE
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent
import pl.lonski.lonitor.screen.GameScreen
import pl.lonski.lonitor.screen.PlayScreen

class StartScreen : GameScreen {

    override fun display(terminal: TileGrid) {
        drawText("Lonitor", Position.offset1x1(), MAGENTA, terminal)
        drawText("[Press enter to start]", Position.create(1, 3), WHITE, terminal)
    }

    override fun handleInput(event: KeyboardEvent): GameScreen? {
        return if (event.code == KeyCode.ENTER) PlayScreen() else null
    }
}
