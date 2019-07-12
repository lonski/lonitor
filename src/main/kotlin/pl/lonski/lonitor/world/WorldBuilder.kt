package pl.lonski.lonitor.world

class WorldBuilder(private val width: Int, private val height: Int) {

    var generator: DungeonGenerator? = null
    var levels: Int = 1

    fun build(): World {
        val tiles = (0..levels).map { generator!!.generate() }.toTypedArray()
        return World(tiles)
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
