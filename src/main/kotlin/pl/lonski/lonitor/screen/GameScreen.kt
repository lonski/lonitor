package pl.lonski.lonitor.screen

import asciiPanel.AsciiPanel
import java.awt.event.KeyEvent

interface GameScreen {

    fun display(terminal: AsciiPanel)

    fun handleInput(key: KeyEvent): GameScreen?
}
