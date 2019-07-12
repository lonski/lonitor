package pl.lonski.lonitor

data class Point(var x: Int, var y: Int, var z: Int) {

    companion object {
        fun getDirections4() =
            listOf(
                Point(-1, 0, 0),
                Point(1, 0, 0),
                Point(0, -1, 0),
                Point(0, 1, 0)
            )
    }
}
