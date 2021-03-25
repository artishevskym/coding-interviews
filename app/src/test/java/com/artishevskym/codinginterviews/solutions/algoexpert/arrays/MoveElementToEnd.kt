package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function that moves all instances of given integer in the array to the end of the array
 * and returns the array. The function should perform this in place and doesn't need to maintain
 * the order of the other integers.
 * @see <a href="https://www.algoexpert.io/questions/Move%20Element%20To%20End">Move Element To End</a>
 */
class MoveElementToEnd {

    @Test
    fun testOptimalSolution() {
        moveElementToEnd(INPUT_ARRAY, TO_MOVE) shouldBeEqualTo EXPECTED_RESULT
    }

    // time: O(n) | space: O(1)
    private fun moveElementToEnd(array: MutableList<Int>, toMove: Int): List<Int> {
        var leftIdx = 0
        var rightIdx = array.size - 1

        while (leftIdx < rightIdx) {
            // move rightIdx inwards when == toMove; sorting in place!
            while (leftIdx < rightIdx && array[rightIdx] == toMove) rightIdx--

            // swap with leftIdx when == toMove
            if (array[leftIdx] == toMove) swap(leftIdx, rightIdx, array)

            // move leftIdx inwards
            leftIdx++
        }

        return array
    }

    private fun swap(i: Int, j: Int, array: MutableList<Int>) {
        val temp = array[j]
        array[j] = array[i]
        array[i] = temp
    }

    private companion object {
        private val INPUT_ARRAY = mutableListOf(2, 1, 2, 2, 2, 3, 4, 2)
        private const val TO_MOVE = 2
        private val EXPECTED_RESULT = listOf(4, 1, 3, 2, 2, 2, 2, 2)
    }
}