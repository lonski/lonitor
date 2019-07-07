package pl.lonski.lonitor.world

class WorldBuilder(private val width: Int, private val height: Int) {

    var tiles: Array<Array<Tile>> = Array(width) { Array(height) { Tile.WALL } }

    fun build(): World {
        return World(tiles)
    }

    fun usingTravellerGenerator(): WorldBuilder {
        tiles = TravelerDungeonGenerator(width, height).generate(0.4f)
        return this
    }
}

