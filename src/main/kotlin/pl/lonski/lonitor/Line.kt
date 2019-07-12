package pl.lonski.lonitor

import kotlin.math.abs

class Line(start: Point, end: Point) {

    val points: List<Point> = calculatePoints(start, end)

    private fun calculatePoints(start: Point, end: Point): List<Point> {
        val pos = Point(start.x, start.y, start.z)
        val list = ArrayList(listOf(Point(pos.x, pos.y, pos.z)))

        val dx = abs(end.x - pos.x)
        val dy = abs(end.y - pos.y)

        val xi = if (start.x < end.x) 1 else -1
        val yi = if (start.y < end.y) 1 else -1

        var err = dx - dy

        while (pos != end) {
            val lastErr = err
            if (lastErr > -dx / 2f) {
                err -= dy
                pos.x += xi
            }
            if (lastErr < dx / 2f) {
                err += dx
                pos.y += yi
            }
            list.add(Point(pos.x, pos.y, pos.z))
        }

        return list
    }
}
