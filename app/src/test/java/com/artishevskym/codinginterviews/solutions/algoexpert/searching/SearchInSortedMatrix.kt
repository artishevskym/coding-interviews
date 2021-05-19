package com.artishevskym.codinginterviews.solutions.algoexpert.searching

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function to find number in sorted matrix (each row is sorted and each column
 * is sorted) and return indices of the target.
 * @see <a href="https://www.algoexpert.io/questions/Search%20In%20Sorted%20Matrix">Search In Sorted Matrix</a>
 */
class SearchInSortedMatrix {

    @Test
    fun testSolution() {
        searchInSortedMatrix(MATRIX, TARGET) shouldBeEqualTo OUTPUT
    }

    // time: O(n+m) | space: O(1)
    private fun searchInSortedMatrix(matrix: List<List<Int>>, target: Int): Pair<Int, Int> {
        // start from top right corner - eliminate whole row or column
        var rowIdx = 0
        var colIdx = matrix[0].size-1

        // iterate through matrix with row/column elimination
        while (rowIdx < matrix.size && colIdx >= 0) {
            if (target < matrix[rowIdx][colIdx]) {
                colIdx--
            } else if (target > matrix[rowIdx][colIdx]) {
                rowIdx++
            } else {
                // target number found
                return Pair(rowIdx, colIdx)
            }
        }


        // target number not found
        return Pair(-1, -1)
    }

    private companion object {
        private val MATRIX = listOf(
            listOf(1, 4, 7, 12, 15, 1000),
            listOf(2, 5, 19, 31, 32, 1001),
            listOf(3, 8, 24, 33, 35, 1002),
            listOf(40, 41, 42, 44, 45, 1003),
            listOf(99, 100, 103, 106, 128, 1004)
        )
        private const val TARGET = 44

        private val OUTPUT = Pair(3, 3)
    }
}