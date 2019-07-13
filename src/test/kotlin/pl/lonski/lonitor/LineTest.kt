package pl.lonski.lonitor

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LineTest {

    @Test
    fun shouldDrawStraightLine() {
        assertThat(Line(Point(0, 0, 0), Point(0, 2, 0)).points).containsExactly(
            Point(0, 0, 0), Point(0, 1, 0), Point(0, 2, 0)
        )
        assertThat(Line(Point(0, 0, 0), Point(-2, 0, 0)).points).containsExactly(
            Point(0, 0, 0), Point(-1, 0, 0), Point(-2, 0, 0)
        )
    }

    @Test
    fun shouldDrawDiagonalLine() {
        //    X
        //   X
        //  X
        // X
        assertThat(Line(Point(0, 0, 0), Point(2, 2, 0)).points).containsExactly(
            Point(0, 0, 0), Point(1, 1, 0), Point(2, 2, 0)
        )

        //   X
        //  X
        //  X
        assertThat(Line(Point(0, 0, 0), Point(1, 2, 0)).points).containsExactly(
            Point(0, 0, 0), Point(0, 1, 0), Point(1, 2, 0)
        )

        //    X
        //   X
        //   X
        //  X
        //  X
        assertThat(Line(Point(0, 0, 0), Point(2, 3, 0)).points).containsExactly(
            Point(0, 0, 0), Point(0, 1, 0), Point(1, 1, 0),
            Point(1, 2, 0), Point(2, 3, 0)
        )

        //    X
        //   X
        //   X
        //  X
        //  X
        // X
        // X
        assertThat((Line(Point(0, 0, 0), Point(3, 6, 0)).points)).containsExactly(
            Point(0, 0, 0), Point(0, 1, 0), Point(1, 2, 0),
            Point(1, 3, 0), Point(2, 4, 0), Point(2, 5, 0), Point(3, 6, 0)
        )
    }
}
