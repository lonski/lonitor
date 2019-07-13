package pl.lonski.lonitor.world

import java.awt.Color

enum class Tile(val glyph: Char, val color: Color) {

    FLOOR('.', Color.GRAY),
    WALL('#', Color.GRAY),
    BOUNDS(' ', Color.GRAY),
    STAIRS_DOWN('>', Color.GRAY),
    STAIRS_UP('<', Color.GRAY),
    UNKNOWN(' ', Color.BLACK);

    fun isGround(): Boolean = this == FLOOR || this == STAIRS_DOWN || this == STAIRS_UP
}
