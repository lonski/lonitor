package pl.lonski.lonitor.world

import pl.lonski.lonitor.Line
import pl.lonski.lonitor.Point
import pl.lonski.lonitor.inRadiusOf

class Fov(private val world: World) {

    private val tiles = Array(world.depth) { Array(world.width) { Array(world.height) { Tile.UNKNOWN } } }
    private var visible = createVisibilityArray()

    private fun createVisibilityArray(): Array<Array<Array<Boolean>>> =
        Array(world.depth) { Array(world.width) { Array(world.height) { false } } }

    fun tile(pos: Point): Tile {
        return tiles[pos.z][pos.x][pos.y]
    }

    fun isVisible(pos: Point): Boolean {
        return visible[pos.z][pos.x][pos.y]
    }

    fun update(center: Point, radius: Int) {
        visible = createVisibilityArray()
        inRadiusOf(radius) { (x, y, z) ->
            val pos = Point(center.x + x, center.y + y, center.z + z)
            if (world.isInBounds(pos)) {
                for (p in Line(center, pos).points) {
                    val tile = world.tile(p)
                    tiles[p.z][p.x][p.y] = tile
                    visible[p.z][p.x][p.y] = true

                    if (!tile.isGround())
                        break
                }
            }
        }
    }
}
