package com.artishevskym.codinginterviews.solutions.algoexpert.sorting

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Implement Selection Sort.
 * @see <a href="https://www.algoexpert.io/questions/Selection%20Sort">Selection Sort</a>
 */
class SelectionSort {

    @Test
    fun testSolution() {
        selectionSort(INPUT_ARRAY) shouldBeEqualTo SORTED_ARRAY
    }

    // Best - time: O(n) | space: O(1)
    // Avg - time: O(n^2) | space: O(1)
    // Worst - time: O(n^2) | space: O(1)
    private fun selectionSort(array: MutableList<Int>): List<Int> {
        if (array.size <= 1) return array

        var currIdx = 0

        while (currIdx < array.size) {
            var minNumIdx = currIdx

            for (i in minNumIdx+1 until array.size) {
                if (array[minNumIdx] > array[i]) minNumIdx = i
            }

            swap(currIdx, minNumIdx, array)

            currIdx++
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