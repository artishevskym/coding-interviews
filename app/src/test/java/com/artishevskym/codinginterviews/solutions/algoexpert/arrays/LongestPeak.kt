package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import kotlin.math.max

/**
 * Write a function returning the length of the longest peak in the array. A peak is defined as
 * adjacent integers in the array are strictly increasing and after tip strictly decreasing.
 */
class LongestPeak {

    @Test
    fun testSolution() {
        longestPeak(SAMPLE_INPUT) shouldBeEqualTo SAMPLE_OUTPUT
    }

    // time: O(n) | space: O(1)
    private fun longestPeak(array: List<Int>): Int {
        var longestPeakLength: Int = 0

        // iterative, traverse once range 1..array.size-2
        var i = 1
        while (i < array.size-1) {
            val isPeak = array[i-1] < array[i] && array[i] > array[i+1]
            if (!isPeak) {
                i++
                continue
            }

            // iterate outwards from the tip to find peak edges
            var leftIdx = i-2
            while (leftIdx >= 0 && array[leftIdx] < array[leftIdx+1]) {
                leftIdx--
            }

            var rightIdx = i+2
            while (rightIdx < array.size && array[rightIdx] < array[rightIdx-1]) {
                rightIdx++
            }

            val currentPeakLength = rightIdx - leftIdx - 1
            longestPeakLength = max(longestPeakLength, currentPeakLength)
            i = rightIdx // optimization
        }

        return longestPeakLength
    }



    private companion object {
        private val SAMPLE_INPUT = listOf(1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3)
        private const val SAMPLE_OUTPUT = 6
    }
}