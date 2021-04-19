package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import kotlin.math.max
import kotlin.math.min

/**
 * Write a function that returns an array of the starting and ending indices of the smallest subarray
 * in the input that needs to be sorted in place in order for the entire input array to be sorted.
 * @see <a href="https://www.algoexpert.io/questions/Subarray%20Sort">Subarray Sort</a>
 */
class SubarraySort {

    @Test
    fun testSolution() {
        subarraySort(INPUT) shouldBeEqualTo OUTPUT
    }

    // time: O(n) | space: O(1)
    private fun subarraySort(array: List<Int>): List<Int> {
        // find min and max numbers out of the order
        var minOutOfOrder = Int.MAX_VALUE
        var maxOutOfOrder = Int.MIN_VALUE

        for (i in array.indices) {
            val num = array[i]
            if (isOutOfOrder(i, num, array)) {
                minOutOfOrder = min(num, minOutOfOrder)
                maxOutOfOrder = max(num, maxOutOfOrder)
            }
        }

        // edge case: sorted array
        if (minOutOfOrder == Int.MAX_VALUE) {
            return listOf<Int>(-1, -1)
        }

        // find final sorted positions
        var resultStartIdx = 0
        while (minOutOfOrder >= array[resultStartIdx]) {
            resultStartIdx++
        }

        var resultEndIdx = array.size-1
        while (maxOutOfOrder <= array[resultEndIdx]) {
            resultEndIdx--
        }

        // return result subarray
        return listOf<Int>(resultStartIdx, resultEndIdx)
    }

    private fun isOutOfOrder(i: Int, num: Int, array: List<Int>): Boolean {
        // check edge cases
        if (i == 0) return num > array[i+1]
        if (i == array.size-1) return num < array[i-1]

        return num < array[i-1] || num > array[i+1]
    }



    private companion object {
        private val INPUT = listOf(1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19)
        private val OUTPUT = listOf(3, 9)
    }
}