package com.artishevskym.codinginterviews.solutions.algoexpert.greedy

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function that returns whether or not a class photo that following guidelines can be taken:
 * - all students wearing red shirts must be in the same row.
 * - all students wearing blue shirts must be in the same row.
 * - each student in the back row must be strictly taller than the student directly in front of
 * them in the front row.
 * @see <a href="https://www.algoexpert.io/questions/Class%20Photos">Class Photos</a>
 */
class ClassPhotos {

    @Test
    fun testSolution() {
        classPhotos(RED_SHIRT_HEIGHTS, BLUE_SHIRT_HEIGHTS) shouldBeEqualTo OUTPUT
    }

    // time: O(nlogn) | space: O(1)
    private fun classPhotos(redShirtHeights: MutableList<Int>, blueShirtHeights: MutableList<Int>): Boolean {
        // find the tallest student - sort desc
        redShirtHeights.sortDescending()
        blueShirtHeights.sortDescending()

        val redShirtInFirstRow = redShirtHeights[0] < blueShirtHeights[0]

        // greedy check if a class photo does not follow the stated guidelines
        for (i in redShirtHeights.indices) {
            if (redShirtInFirstRow) {
                if (redShirtHeights[i] >= blueShirtHeights[i]) return false
            } else {
                if (blueShirtHeights[i] >= redShirtHeights[i]) return false
            }
        }

        // a class photo can be taken
        return true
    }

    private companion object {
        private val RED_SHIRT_HEIGHTS = mutableListOf(5, 8, 1, 3, 4)
        private val BLUE_SHIRT_HEIGHTS = mutableListOf(6, 9, 2, 4, 5)
        private const val OUTPUT = true
    }
}