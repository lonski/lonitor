package pl.lonski.lonitor

import org.hexworks.zircon.api.*
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.uievent.KeyboardEvent
import org.hexworks.zircon.api.uievent.KeyboardEventHandler
import org.hexworks.zircon.api.uievent.KeyboardEventType.KEY_PRESSED
import org.hexworks.zircon.api.uievent.UIEventPhase
import org.hexworks.zircon.api.uievent.UIEventResponse

class Lonitor {

    private var screen: GameScreen = StartScreen()

    fun start() {
        val terminal = createTerminal()
        screen.display(terminal)
        terminal.onKeyboardEvent(KEY_PRESSED, object : KeyboardEventHandler {
            override fun handle(event: KeyboardEvent, phase: UIEventPhase): UIEventResponse {
                val newScreen: GameScreen? = screen.handleInput(event)
                if (newScreen != null) {
                    screen = newScreen
                }
                screen.display(terminal)
                return UIEventResponses.pass()
            }
        })
    }

    private fun createTerminal(): TileGrid {
        return SwingApplications.startTileGrid(
            AppConfigs.newConfig()
                .withDefaultTileset(CP437TilesetResources.mdCurses16x16())
                .withSize(Sizes.create(80, 43))
                .build()
        )
    }
}

fun main() {
    Lonitor().start()
}
