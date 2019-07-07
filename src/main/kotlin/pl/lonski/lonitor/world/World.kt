package pl.lonski.lonitor.world

import org.hexworks.zircon.api.color.ANSITileColor
import pl.lonski.lonitor.Point

class World(private var tiles: Array<Array<Tile>>) {

    val width = tiles.size
    val height = tiles[0].size

    fun glyph(pos: Point): Char {
        return tiles[pos.x][pos.y].glyph
    }

    fun color(pos: Point): ANSITileColor {
        return tiles[pos.x][pos.y].color
    }

    fun tile(pos: Point): Tile {
        if (pos.x < 0 || pos.x >= width || pos.y < 0 || pos.y >= height)
            return Tile.BOUNDS

        return tiles[pos.x][pos.y]
    }

    fun getEmptyLocation(): Point {
        var pos: Point
        do {
            pos = Point((Math.random() * width).toInt(), (Math.random() * height).toInt())
        } while (!tile(pos).isGroud())

        return pos
    }
}
