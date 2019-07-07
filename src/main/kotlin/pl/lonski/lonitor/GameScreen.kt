package pl.lonski.lonitor

import org.hexworks.zircon.api.CharacterTileStrings
import org.hexworks.zircon.api.color.ANSITileColor
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.uievent.KeyboardEvent

interface GameScreen {

    fun display(terminal: TileGrid)
    fun handleInput(event: KeyboardEvent): GameScreen?

    fun drawText(text: String, position: Position, color: ANSITileColor, terminal: TileGrid) {
        CharacterTileStrings.newBuilder()
            .withText(text)
            .withForegroundColor(color)
            .build()
            .toTileGraphic(terminal.currentTileset())
            .drawOnto(terminal, position)
    }
}
