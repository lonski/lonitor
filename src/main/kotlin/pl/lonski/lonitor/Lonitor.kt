package pl.lonski.lonitor

import asciiPanel.AsciiPanel
import pl.lonski.lonitor.screen.GameScreen
import pl.lonski.lonitor.screen.StartScreen
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame


class Lonitor : JFrame(), KeyListener {

    private val terminal = AsciiPanel(80, 24)
    private var screen: GameScreen = StartScreen()

    fun start() {
        add(terminal)
        pack()
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
        addKeyListener(this)
        repaint()
    }

    override fun repaint() {
        terminal.clear()
        screen.display(terminal)
        super.repaint()
    }

    override fun keyPressed(e: KeyEvent?) {
        if (e != null) {
            val newScreen = screen.handleInput(e)
            if (newScreen != null) {
                screen = newScreen
            }
            repaint()
        }
    }

    override fun keyTyped(e: KeyEvent?) {}

    override fun keyReleased(e: KeyEvent?) {}
}


fun main() {
    Lonitor().start()
}
