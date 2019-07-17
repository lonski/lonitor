package pl.lonski.lonitor.item

import pl.lonski.lonitor.world.World
import java.awt.Color

class ItemFactory(private val world: World) {

    fun newRock(depth: Int) {
        val rock = Item("rock", '*', Color.DARK_GRAY)
        world.putAtEmptyLocation(rock, depth)
    }
}
