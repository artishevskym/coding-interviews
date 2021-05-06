package com.artishevskym.codinginterviews.solutions.algoexpert.searching

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function to find 3 largest numbers without sorting the input array.
 *
 * @see <a href="https://www.algoexpert.io/questions/Find%20Three%20Largest%20Numbers">Find Three Largerst Numbers</a>
 */
class FindThreeLargestNumbers {

    @Test
    fun testSolution() {
        findThreeLargestNumbers(ARRAY) shouldBeEqualTo OUTPUT
    }

    // O(n) time | O(1) space
    private fun findThreeLargestNumbers(array: List<Int>): List<Int> {
        val threeLargest = mutableListOf(Int.MIN_VALUE, Int.MIN_VALUE, Int.MIN_VALUE)

        for (num in array) {
            updateLargest(threeLargest, num)
        }

        return threeLargest
    }

    private fun updateLargest(threeLargest: MutableList<Int>, num: Int) {
        if (num > threeLargest[2]) {
            shiftAndUpdate(threeLargest, num, 2)
        } else if (num > threeLargest[1]) {
            shiftAndUpdate(threeLargest, num, 1)
        } else if (num > threeLargest[0]) {
            shiftAndUpdate(threeLargest, num, 0)
        }
    }

    private fun shiftAndUpdate(array: MutableList<Int>, num: Int, idx: Int) {
        for (i in 0 until idx + 1) {
            if (i == idx) {
                array[i] = num
            } else {
                array[i] = array[i + 1]
            }
        }
    }

    private companion object {
        private val ARRAY = listOf(141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7)
        private val OUTPUT = listOf(18, 141, 541)
    }
}