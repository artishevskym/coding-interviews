package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function to transform two-dimensional array into one-dimensional array
 * of all elements in spiral order
 * @see <a href="https://www.algoexpert.io/questions/Spiral%20Traverse">Spiral Traverse</a>
 */
class SpiralTraverse {

    @Test
    fun testIterativeSolution() {
        spiralTraverseIterative(SAMPLE_INPUT) shouldBeEqualTo SAMPLE_OUTPUT
    }

    // time: O(n) | space: O(n)
    private fun spiralTraverseIterative(array: List<List<Int>>): List<Int> {
        val result = mutableListOf<Int>()

        var startRow = 0
        var endRow = array.size-1
        var startColumn = 0
        var endColumn = array[0].size-1

        // loop through array
        while (startRow <= endRow && startColumn <= endColumn) {
            // top edge
            for (column in startColumn..endColumn) {
                result.add(array[startRow][column])
            }

            // right edge
            for (row in startRow+1..endRow) {
                result.add(array[row][endColumn])
            }

            // bottom edge
            if (startRow != endRow) {
                for (column in endColumn-1 downTo startColumn) {
                    result.add(array[endRow][column])
                }
            } // otherwise edge case with single row already counted.

            // left edge
            if (startColumn != endColumn) {
                for (row in endRow-1 downTo startRow+1) {
                    result.add(array[row][startColumn])
                }
            } // otherwise edge case with single column already counted.

            // update indices inwards
            startRow++
            endRow--
            startColumn++
            endColumn--
        }

        return result
    }

    @Test
    fun testRecursiveSolution() {
        spiralTraverseRecursive(SAMPLE_INPUT) shouldBeEqualTo SAMPLE_OUTPUT
    }

    // time: O(n) | space: O(n)
    private fun spiralTraverseRecursive(array: List<List<Int>>): List<Int> {
        val result = mutableListOf<Int>()

        recursiveHelper(array, 0, array.size-1, 0, array[0].size-1, result)

        return result
    }

    private fun recursiveHelper(
        array: List<List<Int>>,
        startRow: Int,
        endRow: Int,
        startColumn: Int,
        endColumn: Int,
        result: MutableList<Int>
    ) {
        // stop condition
        if (startRow > endRow || startColumn > endColumn) return

        // top edge
        for (column in startColumn..endColumn) {
            result.add(array[startRow][column])
        }

        // right edge
        for (row in startRow+1..endRow) {
            result.add(array[row][endColumn])
        }

        // bottom edge
        if (startRow != endRow) {
            for (column in endColumn-1 downTo startColumn) {
                result.add(array[endRow][column])
            }
        } // otherwise edge case with single row already counted.

        // left edge
        if (startColumn != endColumn) {
            for (row in endRow-1 downTo startRow+1) {
                result.add(array[row][startColumn])
            }
        } // otherwise edge case with single column already counted.

        // recursive call
        recursiveHelper(array, startRow+1, endRow-1, startColumn+1, endColumn-1, result)
    }

    private companion object {
        private val SAMPLE_INPUT = listOf(
            listOf(1, 2, 3, 4),
            listOf(12, 13, 14, 5),
            listOf(11, 16, 15, 6),
            listOf(10, 9, 8, 7)
        )

        private val SAMPLE_OUTPUT = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
    }
}