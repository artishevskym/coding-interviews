package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Find all triplets in the array that sum up to a target sum and return a two-dimensional
 * array of all these triplets. The numbers in each triplet should be ordered in ascending
 * order with respect to the numbers they hold.
 * @see <a href="https://www.algoexpert.io/questions/Three%20Number%20Sum">Three Number Sum</a>
 */
class ThreeNumberSum {

    @Test
    fun testSortingSolution() {
        threeNumberSum(INPUT_ARRAY, TARGET_SUM) shouldBeEqualTo EXPECTED_RESULT
    }

    // time: O(n^2) | space: O(n)
    private fun threeNumberSum(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
        // first we need ascending order
        array.sort()

        // if no triplet found, we return empty list
        val triplets = mutableListOf<MutableList<Int>>()

        for (i in 0 until array.size - 2) {
            var left = i + 1
            var right = array.size - 1

            // take current i elem and try to find two number making target sum
            while (left < right) {
                val currentSum = array[i] + array[left] + array[right]

                when {
                    currentSum == targetSum -> {
                        triplets.add(mutableListOf(array[i], array[left], array[right]))
                        // check if there is no more pair matching to current i elem
                        left++
                        right--
                    }
                    currentSum < targetSum -> left++
                    currentSum > targetSum -> right--
                }
            }
        }

        return triplets
    }

    private companion object {
        private val INPUT_ARRAY = mutableListOf(12, 3, 1, 2, -6, 5, -8, 6)
        private const val TARGET_SUM = 0
        private val EXPECTED_RESULT = mutableListOf(
            mutableListOf(-8, 2, 6),
            mutableListOf(-8, 3, 5),
            mutableListOf(-6, 1, 5)
        )
    }
}