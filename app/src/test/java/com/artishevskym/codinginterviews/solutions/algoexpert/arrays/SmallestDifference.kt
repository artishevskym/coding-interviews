package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function that takes in two non-empty arrays of integers and finds the pair of numbers
 * (one from each array) whose absolute difference is closest to zero, and returns these numbers
 * in array.
 * @see <a href="https://www.algoexpert.io/questions/Smallest%20Difference">Smallest Difference</a>
 */
class SmallestDifference {

    @Test
    fun testSortingSolution() {
        smallestDifference(ARRAY_ONE, ARRAY_TWO) shouldBeEqualTo EXPECTED_RESULT
    }

    // time: O(nlog(n) + nlog(m)) | space: O(1)
    private fun smallestDifference(arrayOne: MutableList<Int>, arrayTwo: MutableList<Int>): List<Int> {
        // first, sort both arrays
        arrayOne.sort()
        arrayTwo.sort()

        // we need two indices, smallest pair and smallest difference
        var idxOne = 0
        var idxTwo = 0
        var smallestPair = listOf<Int>()
        var smallest = Int.MAX_VALUE
        var current: Int


        while (idxOne < arrayOne.size && idxTwo < arrayTwo.size) {
            val firstNum = arrayOne[idxOne]
            val secondNum = arrayTwo[idxTwo]

            when {
                firstNum < secondNum -> {
                    current = secondNum - firstNum
                    idxOne++
                }
                secondNum < firstNum -> {
                    current = firstNum - secondNum
                    idxTwo++
                }
                else -> {
                    // if equals to each other, it's our pair
                    return listOf(firstNum, secondNum)
                }
            }

            // if current distance is smaller than smallest, update
            if (smallest > current) {
                smallest = current
                smallestPair = listOf(firstNum, secondNum)
            }

            // keep looping till the end of one of arrays
        }

        return smallestPair
    }

    private companion object {
        private val ARRAY_ONE = mutableListOf(-1, 5, 10, 20, 28, 3)
        private val ARRAY_TWO = mutableListOf(26, 134, 135, 15, 17)
        private val EXPECTED_RESULT = listOf(28, 26)
    }
}