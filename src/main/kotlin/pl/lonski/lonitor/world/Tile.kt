package pl.lonski.lonitor.world

import org.hexworks.zircon.api.color.ANSITileColor

enum class Tile(val glyph: Char, val color: ANSITileColor) {

    FLOOR('.', ANSITileColor.GRAY),
    WALL('#', ANSITileColor.GRAY),
    BOUNDS(' ', ANSITileColor.GRAY);

    fun isGroud(): Boolean = this == FLOOR
}
