package pl.lonski.lonitor

import org.hexworks.zircon.api.AppConfigs
import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.Sizes
import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.kotlin.onInput
import org.hexworks.zircon.api.kotlin.whenKeyStroke

class Lonitor {

    private var screen: GameScreen = StartScreen()

    fun start() {
        val terminal = createTerminal()
        screen.display(terminal)
        terminal.onInput { input ->
            input.whenKeyStroke { ks ->
                val newScreen: GameScreen? = screen.handleInput(ks)
                if (newScreen != null) {
                    this.screen = newScreen
                }
                screen.display(terminal)
            }
        }
    }

    private fun createTerminal(): TileGrid {
        return SwingApplications.startTileGrid(
            AppConfigs.newConfig()
                .withSize(Sizes.create(80, 43))
                .withDefaultTileset(CP437TilesetResources.mdCurses16x16())
                .build()
        )
    }
}

fun main() {
    Lonitor().start()
}
