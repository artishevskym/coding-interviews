package com.artishevskym.codinginterviews.solutions.algoexpert.sorting

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Implement Insertion Sort.
 * @see <a href="https://www.algoexpert.io/questions/Insertion%20Sort">Insertion Sort</a>
 */
class InsertionSort {

    @Test
    fun testSolution() {
        insertionSort(INPUT_ARRAY) shouldBeEqualTo SORTED_ARRAY
    }

    private fun insertionSort(array: MutableList<Int>): List<Int> {
        if (array.size <= 1) return array

        // divide array into two parts, left should be sorted at all times
        for (i in 1 until array.size) {
            // take number at i position
            var j = i

            // insert in correct place in left sorted sub array
            while (j > 0 && array[j] < array[j-1]) {
                swap(j, j-1, array)
                j--
            }
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