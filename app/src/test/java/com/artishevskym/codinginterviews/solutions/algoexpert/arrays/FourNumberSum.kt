package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function to find all quadruplets in the array that sum up to the target sum and return
 * a two-dimensional array of all these quadruplets in no particular order.
 * @see <a href="https://www.algoexpert.io/questions/Four%20Number%20Sum">Four Number Sum</a>
 */
class FourNumberSum {

    @Test
    fun testSolution() {
        fourNumberSum(INPUT, TARGET_SUM) shouldBeEqualTo OUTPUT
    }

    // Avg: O(n^2) time || O(n^2) space
    // Worst: O(n^3) time | O(n^2) space
    private fun fourNumberSum(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
        val allPairSums = mutableMapOf<Int, MutableList<MutableList<Int>>>()
        val quadruplets = mutableListOf<MutableList<Int>>()

        // to prevent double counting quadruplets, iterate in one single for loop
        for (i in 1 until array.size - 1) {
            // go right from i and find values matching to allPairSums
            for (j in i + 1 until array.size) { // regularly j is i +1 to array end
                val currentSum = array[i] + array[j]
                val difference = targetSum - currentSum

                if (allPairSums.containsKey(difference)) {
                    for (pair in allPairSums[difference]!!) { // worst scenario: it's 3rd for loop iteration over n
                        val p = pair.toMutableList<Int>()
                        p.add(array[i])
                        p.add(array[j])
                        quadruplets.add(p)
                    }
                }
            }

            // go left from i and insert all i-pairs into allPairSum
            for (k in 0 until i) { // iterating backwards
                val currentSum = array[i] + array[k]
                if (!allPairSums.containsKey(currentSum)) {
                    // need to add new key value pair
                    allPairSums[currentSum] = mutableListOf<MutableList<Int>>()
                    // and add value
                    allPairSums[currentSum]!!.add(mutableListOf<Int>(array[k], array[i]))
                } else {
                    // need to add value to the key
                    allPairSums[currentSum]!!.add(mutableListOf<Int>(array[k], array[i]))
                }
            }
        }

        return quadruplets
    }

    private companion object {
        private val INPUT = mutableListOf(7, 6, 4, -1, 1, 2)
        private const val TARGET_SUM = 16
        private val OUTPUT = listOf(listOf(7, 6, 4, -1), listOf(7, 6, 1, 2))
    }
}