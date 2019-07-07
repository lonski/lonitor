package pl.lonski.lonitor

import org.hexworks.zircon.api.AppConfigs
import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.Sizes
import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.application.Application
import org.hexworks.zircon.api.uievent.KeyboardEvent
import org.hexworks.zircon.api.uievent.KeyboardEventProcessor
import org.hexworks.zircon.api.uievent.KeyboardEventType.KEY_PRESSED
import org.hexworks.zircon.api.uievent.UIEventPhase
import pl.lonski.lonitor.screen.GameScreen
import pl.lonski.lonitor.screen.StartScreen

class Lonitor {

    private val app = createApp()
    private var screen: GameScreen = StartScreen()

    fun start() {
        app.start()
        screen.display(app.tileGrid)
        app.tileGrid.processKeyboardEvents(KEY_PRESSED, object : KeyboardEventProcessor {
            override fun process(event: KeyboardEvent, phase: UIEventPhase) {
                val newScreen: GameScreen? = screen.handleInput(event)
                if (newScreen != null) {
                    screen = newScreen
                }
                screen.display(app.tileGrid)
            }
        })
    }

    private fun createApp(): Application {
        return SwingApplications.buildApplication(
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
