package pl.lonski.lonitor.screen

import asciiPanel.AsciiPanel
import java.awt.Color
import java.awt.event.KeyEvent

class StartScreen : GameScreen {

    override fun display(terminal: AsciiPanel) {
        terminal.write("Lonitor", 1, 1, Color.MAGENTA)
        terminal.write("[Press enter to start]", 1, 2, Color.WHITE)
    }

    override fun handleInput(key: KeyEvent): GameScreen? {
        return if (key.keyCode == KeyEvent.VK_ENTER) PlayScreen() else null
    }
}
