package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function to find the largest range of integers contained in that array.
 * @see <a href="https://www.algoexpert.io/questions/Largest%20Range">Largest Range</a>
 */
class LargestRange {

    @Test
    fun testSolution() {
        largestRange(INPUT) shouldBeEqualTo OUTPUT
    }

    // time: O(n) | space: O(n)
    private fun largestRange(array: List<Int>): Pair<Int, Int> {
        var result = Pair(array[0], array[0])
        var longestLength = 1

        val visitedNums = mutableMapOf<Int, Boolean>()
        for (elem in array) {
            visitedNums[elem] = false
        }

        for (elem in array) {
            if (visitedNums[elem] == true) continue

            visitedNums[elem] = true

            // expand from current element
            var currentLength = 1
            var left = elem - 1
            while (visitedNums.containsKey(left)) {
                visitedNums[left] = true
                currentLength++
                left--
            }

            var right = elem + 1
            while (visitedNums.containsKey(right)) {
                visitedNums[right] = true
                currentLength++
                right++
            }

            if (currentLength > longestLength) {
                longestLength = currentLength
                result = Pair(left + 1, right - 1)
            }
        }

        return result
    }

    private companion object {
        private val INPUT = listOf(1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6)
        private val OUTPUT = Pair(0, 7)
    }
}