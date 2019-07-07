package pl.lonski.lonitor.world

import org.hexworks.zircon.api.color.ANSITileColor

class World(var tiles: Array<Array<Tile>>) {

    val width = tiles.size
    val height = tiles[0].size

    fun glyph(x: Int, y: Int): Char {
        return tiles[x][y].glyph
    }

    fun color(x: Int, y: Int): ANSITileColor {
        return tiles[x][y].color
    }
}
