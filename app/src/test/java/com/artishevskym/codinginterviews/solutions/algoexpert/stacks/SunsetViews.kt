package com.artishevskym.codinginterviews.solutions.algoexpert.stacks

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import kotlin.math.max

/**
 * Write function that converts an array of buildings and a direction that all of the building face,
 * into an array of the indices of the buildings that can see the sunset.
 *
 * Note: the indices in the output array should be sorted in ascending order.
 *
 * @see <a href="https://www.algoexpert.io/questions/Sunset%20Views">Sunset Views</a>
 */
class SunsetViews {

    @Test
    fun testSolutionUsingTrackOfMaximumBuildingHeight() {
        sunsetViews(BUILDINGS, DIRECTION1) shouldBeEqualTo OUTPUT1
        sunsetViews(BUILDINGS, DIRECTION2) shouldBeEqualTo OUTPUT2
    }

    // time: O(n) | space: O(n)
    private fun sunsetViews(buildings: List<Int>, direction: String): List<Int> {
        val buildingsWithSunsetViews = mutableListOf<Int>()
        val startIndex = if (direction == "EAST") buildings.size - 1 else 0
        val step = if (direction == "EAST") -1 else 1
        var i = startIndex
        var currentMaxHeight = 0

        while (i >= 0 && i < buildings.size) {
            if (buildings[i] > currentMaxHeight) buildingsWithSunsetViews.add(i)
            currentMaxHeight = max(currentMaxHeight, buildings[i])
            i += step
        }

        if (direction == "EAST") buildingsWithSunsetViews.reverse()

        return buildingsWithSunsetViews
    }


    @Test
    fun testSolutionUsingStack() {
        sunsetViewsWithStack(BUILDINGS, DIRECTION1) shouldBeEqualTo OUTPUT1
        sunsetViewsWithStack(BUILDINGS, DIRECTION2) shouldBeEqualTo OUTPUT2
    }

    // time: O(n) | space: O(n)
    private fun sunsetViewsWithStack(buildings: List<Int>, direction: String): List<Int> {
        val result = mutableListOf<Int>()
        val startIndex = if (direction == "EAST") 0 else buildings.size-1
        val step = if (direction == "EAST") 1 else -1
        var i = startIndex

        while (i >= 0 && i < buildings.size) {
            while (result.size > 0 && buildings[result[result.size-1]] <= buildings[i]) {
                result.removeAt(result.size-1)
            }
            result.add(i)
            i += step
        }


        if (direction == "WEST") result.reverse()

        return result
    }

    private companion object {
        private val BUILDINGS = mutableListOf(3, 5, 4, 4, 3, 1, 3, 2)
        private const val DIRECTION1 = "EAST"
        private val OUTPUT1 = listOf(1, 3, 6, 7)
        private const val DIRECTION2 = "WEST"
        private val OUTPUT2 = listOf(0, 1)
    }
}