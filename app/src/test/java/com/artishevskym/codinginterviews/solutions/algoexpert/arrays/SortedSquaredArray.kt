package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import kotlin.math.abs

/**
 * Write a function that takes array of integers that are sorted in ascending order and returns
 * a new array of the same length with the squares of original array sorted in ascending order.
 * @see <a href="https://www.algoexpert.io/questions/Sorted%20Squared%20Array">Sorted Squared Array</a>
 */
class SortedSquaredArray {

    @Test
    fun testBruteForce1() {
        bruteForce1(INPUT_ARRAY) shouldBeEqualTo EXPECTED_RESULT
    }

    @Test
    fun testBruteForce2() {
        bruteForce2(INPUT_ARRAY) shouldBeEqualTo EXPECTED_RESULT
    }

    @Test
    fun testOptimalSolution() {
        sortedSquaredArray(INPUT_ARRAY) shouldBeEqualTo EXPECTED_RESULT
    }

    // time: O(nlogn) | space: O(n)
    private fun bruteForce1(array: List<Int>): List<Int> {
        val result = MutableList(array.size) { 0 }

        for (i in array.indices) {
            result[i] = array[i] * array[i]
        }

        result.sort()
        return result
    }

    // time: O(nlogn) | space: O(n)
    private fun bruteForce2(array: List<Int>) = array.map { it * it }.toMutableList().apply { sort() }

    // time: O(n) | space: O(n)
    private fun sortedSquaredArray(array: List<Int>): List<Int> {
        val result = MutableList(array.size) { 0 }
        var leftIdx = 0
        var rightIdx = array.size - 1

        for (i in array.size - 1 downTo 0) {
            val leftValue = abs(array[leftIdx])
            val rightValue = abs(array[rightIdx])

            if (leftValue > rightValue) {
                result[i] = leftValue * leftValue
                leftIdx += 1
            } else {
                result[i] = rightValue * rightValue
                rightIdx -= 1
            }
        }

        return result
    }

    private companion object {
        private val INPUT_ARRAY = listOf(-3, 1, 2, 5, 6, 8, 9)
        private val EXPECTED_RESULT = listOf(1, 4, 9, 25, 36, 64, 81)
    }
}