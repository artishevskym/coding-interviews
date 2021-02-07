package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldContainSame
import org.junit.Test

/**
 * Find any two numbers sum up to the target sum.
 * @see <a href="https://www.algoexpert.io/questions/Two%20Number%20Sum">Two Number Sum</a>
 */
class TwoNumberSum {

    @Test
    fun testBruteForceSolution() {
        solveUsingTwoForLoops(INPUT_ARRAY, TARGET_SUM) shouldContainSame EXPECTED_RESULT
    }

    // time: O(n^2) | space: O(1)
    private fun solveUsingTwoForLoops(array: MutableList<Int>, targetSum: Int): List<Int> {
        for (i in 0 until array.size - 1) {
            for (j in i + 1 until array.size) {
                if (array[i] + array[j] == targetSum) {
                    return listOf(array[i], array[j])
                }
            }
        }

        return emptyList()
    }

    @Test
    fun testHashTableSolution() {
        solveUsingHashTable(INPUT_ARRAY, TARGET_SUM) shouldContainSame EXPECTED_RESULT
    }

    // OPTIMAL SOLUTION
    // time: O(n) | space: O(n)
    private fun solveUsingHashTable(array: MutableList<Int>, targetSum: Int): List<Int> {
        val hashTable = mutableMapOf<Int, Boolean>() // extra space used

        for (number in array) {
            val potentialMatch = targetSum - number

            if (hashTable.containsKey(potentialMatch)) { // hashTable.containsKey() is O(1)
                return listOf(number, potentialMatch)
            } else {
                hashTable[number] = true // value is not important here
            }
        }

        return emptyList()
    }

    @Test
    fun testSortingSolution() {
        solveUsingSorting(INPUT_ARRAY, TARGET_SUM) shouldContainSame EXPECTED_RESULT
    }

    // time: O(nlog(n)) | space: O(1)
    private fun solveUsingSorting(array: MutableList<Int>, targetSum: Int): List<Int> {
        array.sort()

        var leftIdx = 0
        var rightIdx = array.size - 1

        while (leftIdx < rightIdx) {
            val currentSum = array[leftIdx] + array[rightIdx]
            if (currentSum == targetSum) return listOf(array[leftIdx], array[rightIdx])
            if (currentSum < targetSum) leftIdx++ else rightIdx--
        }

        return emptyList()
    }

    private companion object {
        private val INPUT_ARRAY = mutableListOf(3, 5, -4, 8, 11, 1, -1, 6)
        private const val TARGET_SUM = 10
        private val EXPECTED_RESULT = listOf(11, -1)
    }
}