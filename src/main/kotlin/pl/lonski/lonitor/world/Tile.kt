package pl.lonski.lonitor.world

import java.awt.Color

enum class Tile(val glyph: Char, val color: Color) {

    FLOOR('.', Color.GRAY),
    WALL('#', Color.GRAY),
    BOUNDS(' ', Color.GRAY);

    fun isGroud(): Boolean = this == FLOOR
}
