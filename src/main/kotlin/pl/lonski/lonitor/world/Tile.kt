package pl.lonski.lonitor.world

import org.hexworks.zircon.api.color.ANSITileColor
import org.hexworks.zircon.api.color.TileColor

enum class Tile(val glyph: Char, val color: TileColor) {

    FLOOR('.', ANSITileColor.GRAY),
    WALL('#', ANSITileColor.GRAY),
    BOUNDS(' ', ANSITileColor.GRAY);

    fun isGroud(): Boolean = this == FLOOR
}
