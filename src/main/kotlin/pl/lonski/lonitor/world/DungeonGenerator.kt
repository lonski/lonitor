package pl.lonski.lonitor.world

interface DungeonGenerator {
    fun generate(): Array<Array<Tile>>
}
