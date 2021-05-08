package com.artishevskym.codinginterviews.solutions.algoexpert.graphs

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function that returns a boolean representing if the jumps in the array form a single cycle.
 *
 * @see <a href="https://www.algoexpert.io/questions/Single%20Cycle%20Check">Single Cycle Check</a>
 */
class SingleCycleCheck {

    @Test
    fun testSolution() {
        hasSingleCycle(INPUT) shouldBeEqualTo OUTPUT
    }

    // time: O(n) | space: O(1)
    private fun hasSingleCycle(array: List<Int>): Boolean {
        val startIndex = 0
        var count = 0
        var currentIndex = startIndex

        while (count < array.size) {
            if (count > 0 && currentIndex == startIndex) return false
            currentIndex = getNextIndex(currentIndex, array)
            count++
        }

        return currentIndex == startIndex // && count == array.size; single cycle condition
    }

    private fun getNextIndex(currentIndex: Int, array: List<Int>) : Int {
        val jump = array[currentIndex]
        var nextIndex = (currentIndex + jump) % array.size
        if (nextIndex < 0) nextIndex += array.size
        return nextIndex
    }

    private companion object {
        private val INPUT = listOf(2,3,1,-4,-4,2)
        private const val OUTPUT = true
    }
}