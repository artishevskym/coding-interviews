package com.artishevskym.codinginterviews.solutions.algoexpert.sorting

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Implement Quick Sort.
 * @see <a href="https://www.algoexpert.io/questions/Quick%20Sort">Quick Sort</a>
 */
class QuickSort {

    @Test
    fun testSolution() {
        quickSort(INPUT) shouldBeEqualTo OUTPUT
    }

    // O(nlog(n)) time | O(log(n)) space
    private fun quickSort(array: MutableList<Int>): List<Int> {
        quickSortHelper(array, 0, array.size - 1)
        return array
    }

    private fun quickSortHelper(array: MutableList<Int>, startIdx: Int, endIdx: Int) {
        if (startIdx >= endIdx) return

        val pivotIdx = startIdx
        var leftIdx = startIdx + 1
        var rightIdx = endIdx

        while (rightIdx >= leftIdx) {
            if (array[leftIdx] > array[pivotIdx] && array[rightIdx] < array[pivotIdx]) {
                swap(leftIdx, rightIdx, array)
            }
            if (array[leftIdx] <= array[pivotIdx]) leftIdx++
            if (array[rightIdx] >= array[pivotIdx]) rightIdx--
        }
        // move pivot to sorted position
        swap(pivotIdx, rightIdx, array)

        // optimization
        val leftSubarrayIsSmaller = rightIdx - 1 - startIdx < endIdx - (rightIdx + 1)
        if (leftSubarrayIsSmaller) {
            quickSortHelper(array, startIdx, rightIdx - 1)
            quickSortHelper(array, rightIdx + 1, endIdx)
        } else {
            quickSortHelper(array, rightIdx + 1, endIdx)
            quickSortHelper(array, startIdx, rightIdx - 1)
        }
    }

    private fun swap(i: Int, j: Int, array: MutableList<Int>) {
        val temp = array[j]
        array[j] = array[i]
        array[i] = temp
    }

    private companion object {
        private val INPUT = mutableListOf(8, 5, 2, 9, 5, 6, 3)
        private val OUTPUT = listOf(2, 3, 5, 5, 6, 8, 9)
    }
}