package pl.lonski.lonitor.screen

import asciiPanel.AsciiPanel
import java.awt.Color
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.VK_ENTER

class LoseScreen : GameScreen {

    override fun display(terminal: AsciiPanel) {
        terminal.writeCenter("Game over.", 2, Color.RED)
        terminal.writeCenter("[press enter to start a new game]", 4)
    }

    override fun handleInput(key: KeyEvent): GameScreen? {
        return if (key.keyCode == VK_ENTER) PlayScreen() else null
    }
}
