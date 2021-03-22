package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function to check an array is monotonic.
 * @see <a href="https://www.algoexpert.io/questions/Monotonic%20Array">Monotonic Array</a>
 */
class MonotonicArray {

    @Test
    fun testIfAnyElementIsBreakingTrend() {
        isMonotonicCheckElementsFollowTrend(INPUT) shouldBeEqualTo RESULT
    }

    // time: O(n) | space: O(1)
    private fun isMonotonicCheckElementsFollowTrend(array: List<Int>): Boolean {
        // Empty arrays and arrays of one element are monotonic
        if (array.size <= 2) return true

        var trend = array[1] - array[0]
        for (i in 2 until array.size) {
            if (trend == 0) {
                trend = array[i] - array[i - 1]
                continue
            }
            if (checkElementsFollowTrend(array[i - 1], array[i], trend)) {
                continue
            } else {
                return false
            }
        }

        // all elements follow trend, array is monotonic
        return true
    }

    private fun checkElementsFollowTrend(previous: Int, current: Int, trend: Int) : Boolean {
        val difference = current - previous
        if (trend > 0) return difference >= 0
        return difference <= 0
    }

    @Test
    fun testTwoAssumptions() {
        isMonotonicCheckTwoAssumptions(INPUT) shouldBeEqualTo RESULT
    }

    // time: O(n) | space: O(1)
    private fun isMonotonicCheckTwoAssumptions(array: List<Int>): Boolean {
        // Let's do assumptions the array is both non-decreasing and non-increasing
        var isNonDecreasing = true
        var isNonIncreasing = true

        for (i in 1 until array.size) {
            if (array[i - 1] > array[i]) isNonDecreasing = false
            if (array[i - 1] < array[i]) isNonIncreasing = false
        }

        return isNonDecreasing || isNonIncreasing
    }

    private companion object {
        private val INPUT = listOf(-1, -5, -10, -1100, -1100, -1101, -1102, -9001)
        private const val RESULT = true
    }
}