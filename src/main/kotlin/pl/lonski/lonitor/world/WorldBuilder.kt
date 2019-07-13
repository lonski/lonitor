package pl.lonski.lonitor.world

import kotlin.random.Random

class WorldBuilder(private val width: Int, private val height: Int) {

    var generator: DungeonGenerator? = null
    var levels: Int = 1
    var tiles: Array<Array<Array<Tile>>> = Array(levels) { Array(width) { Array(height) { Tile.WALL } } }

    fun build(): World {
        tiles = (0..levels).map { generator!!.generate() }.toTypedArray()
        connectLevels()
        return World(tiles)
    }

    private fun connectLevels() {
        (1..levels).forEach { n ->
            val dungN = tiles[n]
            val dungNm1 = tiles[n - 1]
            var x = 0
            var y = 0
            var found = false
            while (!found) {
                x = Random.nextInt(width)
                y = Random.nextInt(height)
                if (dungN[x][y] == Tile.FLOOR && dungNm1[x][y] == Tile.FLOOR)
                    found = true
            }
            dungN[x][y] = Tile.STAIRS_UP
            dungNm1[x][y] = Tile.STAIRS_DOWN
        }
    }

    fun usingTravellerGenerator(): WorldBuilder {
        generator = TravelerDungeonGenerator(width, height, 0.4f)
        return this
    }

    fun levels(levels: Int): WorldBuilder {
        this.levels = levels
        return this
    }
}
