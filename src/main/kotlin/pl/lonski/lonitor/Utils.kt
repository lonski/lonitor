package pl.lonski.lonitor

fun inRadiusOf(r: Int, f: (Point) -> Unit) {
    for (ox in -r..r) for (oy in -r..r) {
        if (ox * ox + oy * oy > r * r) continue
        f(Point(ox, oy, 0))
    }
}

