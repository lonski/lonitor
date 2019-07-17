package pl.lonski.lonitor.item

import java.awt.Color

class Item(
    private val name: String,
    private val glyph: Char,
    private val color: Color
) {
    fun name(): String = name
    fun glyph(): Char = glyph
    fun color(): Color = color
}
