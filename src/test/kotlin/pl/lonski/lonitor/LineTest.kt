package pl.lonski.lonitor

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LineTest {

    @Test
    fun shouldDrawStraightLine() {
        assertThat(Line(Point(0, 0), Point(0, 2)).points).containsExactly(
            Point(0, 0), Point(0, 1), Point(0, 2)
        )
        assertThat(Line(Point(0, 0), Point(-2, 0)).points).containsExactly(
            Point(0, 0), Point(-1, 0), Point(-2, 0)
        )
    }

    @Test
    fun shouldDrawDiagonalLine() {
        //    X
        //   X
        //  X
        // X
        assertThat(Line(Point(0, 0), Point(2, 2)).points).containsExactly(
            Point(0, 0), Point(1, 1), Point(2, 2)
        )

        //   X
        //  X
        //  X
        assertThat(Line(Point(0, 0), Point(1, 2)).points).containsExactly(
            Point(0, 0), Point(0, 1), Point(1, 2)
        )

        //    X
        //   X
        //   X
        //  X
        //  X
        assertThat(Line(Point(0, 0), Point(2, 3)).points).containsExactly(
            Point(0, 0), Point(0, 1), Point(1, 1), Point(1, 2), Point(2, 3)
        )

        //    X
        //   X
        //   X
        //  X
        //  X
        // X
        // X
        assertThat((Line(Point(0, 0), Point(3, 6)).points)).containsExactly(
            Point(0, 0), Point(0, 1), Point(1, 2), Point(1, 3),
            Point(2, 4), Point(2, 5), Point(3, 6)
        )
    }
}
