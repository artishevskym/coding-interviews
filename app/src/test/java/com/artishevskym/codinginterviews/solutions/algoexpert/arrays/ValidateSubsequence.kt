package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Check if the second array is a subsequence of the first one.
 * @see <a href="https://www.algoexpert.io/questions/Validate%20Subsequence">Validate Subsequence</a>
 */
class ValidateSubsequence {

    @Test
    fun testWhileLoopSolution() {
        isValidSubsequenceUsingWhileLoop(ARRAY, SEQUENCE) shouldBeEqualTo EXPECTED_RESULT
    }

    // time: O(n) | space: O(1)
    private fun isValidSubsequenceUsingWhileLoop(array: List<Int>, sequence: List<Int>): Boolean {
        var arrIndex = 0
        var seqIndex = 0

        while (arrIndex < array.size && seqIndex < sequence.size) {
            if (array[arrIndex] == sequence[seqIndex]) seqIndex++
            arrIndex++
        }

        return seqIndex == sequence.size
    }

    @Test
    fun testForLoopSolution() {
        isValidSubsequenceUsingForLoop(ARRAY, SEQUENCE) shouldBeEqualTo EXPECTED_RESULT
    }

    // time: O(n) | space: O(1)
    private fun isValidSubsequenceUsingForLoop(array: List<Int>, sequence: List<Int>): Boolean {
        var seqIdx = 0

        for (value in array) {
            if (seqIdx == sequence.size) break // prevent IndexOutOfBoundsException
            if (sequence[seqIdx] == value) seqIdx++
        }

        return seqIdx == sequence.size
    }

    private companion object {
        private val ARRAY = mutableListOf(5, 1, 22, 25, 6, -1, 8, 10, 11)
        private val SEQUENCE = mutableListOf(1, 6, -1, 10)
        private const val EXPECTED_RESULT = true
    }
}