package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import kotlin.math.abs
import kotlin.math.min

/**
 * Write a function to find first duplicate value in an array of integers between 1 and n.
 * Note: you're allowed to mutate the input array.
 * @see <a href="https://www.algoexpert.io/questions/First%20Duplicate%20Value">First Duplicate Value</a>
 */
class FirstDuplicateValue {

    @Test
    fun testNaiveSolution() {
        firstDuplicateValueNaive(SAMPLE_INPUT) shouldBeEqualTo SAMPLE_OUTPUT
    }

    // O(n^2) time | O(1) space - where n is the length of the input array
    private fun firstDuplicateValueNaive(array: MutableList<Int>): Int {
        var minimumSecondIndex = array.size

        for (i in 0 until array.size) {
            val value = array[i]

            for (j in i + 1 until array.size) {
                val valueToCompare = array[j]

                if (value == valueToCompare) {
                    minimumSecondIndex = min(minimumSecondIndex, j)
                }
            }
        }

        if (minimumSecondIndex == array.size) return -1

        return array[minimumSecondIndex]
    }

    @Test
    fun testSolutionWithConstantTimeLookUpsStructure() {
        firstDuplicateValueUsingSet(SAMPLE_INPUT) shouldBeEqualTo SAMPLE_OUTPUT
    }

    // O(n) time | O(n) space
    private fun firstDuplicateValueUsingSet(array: MutableList<Int>): Int {
        val seen = mutableSetOf<Int>() // structure with constant time lookups to keep track of seen already
        for (value in array) {
            if (value in seen) return value
            seen.add(value)
        }
        return -1
    }

    @Test
    fun testSolutionMutatingInputArray() {
        firstDuplicateValueUsingInputArrayMutation(SAMPLE_INPUT.toMutableList()) shouldBeEqualTo SAMPLE_OUTPUT
    }

    // O(n) time | O(1) space
    private fun firstDuplicateValueUsingInputArrayMutation(array: MutableList<Int>): Int {
        for (value in array) {
            val absValue = abs(value)
            if (array[absValue - 1] < 0) return absValue
            array[absValue - 1] *= -1
        }
        return -1
    }

    private companion object {
        private val SAMPLE_INPUT = mutableListOf(2, 1, 5, 2, 3, 3, 4)
        private const val SAMPLE_OUTPUT = 2 // 2 is the first integer that appears more than once
    }
}