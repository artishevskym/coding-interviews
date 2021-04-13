package com.artishevskym.codinginterviews.solutions.algoexpert.sorting

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Implement Bubble Sort.
 * @see <a href="https://www.algoexpert.io/questions/Bubble%20Sort">Bubble Sort</a>
 */
class BubbleSort {

    @Test
    fun testSolution() {
        bubbleSort(INPUT_ARRAY) shouldBeEqualTo SORTED_ARRAY
    }

    // Best - time: O(n) | space: O(1)
    // Avg - time: O(n^2) | space: O(1)
    // Worst - time: O(n^2) | space: O(1)
    private fun bubbleSort(array: MutableList<Int>): List<Int> {
        // nothing to sort
        if (array.size <= 1) return array

        var isSorted = false
        var counter = 0 // optimization

        while (!isSorted) {
            isSorted = true

            for (i in 0 until array.size-1 - counter) {
                if (array[i] > array[i+1]) {
                    swap(i, i+1, array)
                    isSorted = false
                }
            }

            // after each iteration the greatest value is shifted to the right
            counter++
        }

        return array
    }

    private fun swap(i: Int, j: Int, array: MutableList<Int>) {
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
    }

    private companion object {
        private val INPUT_ARRAY = mutableListOf(8, 5, 2, 9, 5, 6, 3)
        private val SORTED_ARRAY = INPUT_ARRAY.sorted()
    }
}