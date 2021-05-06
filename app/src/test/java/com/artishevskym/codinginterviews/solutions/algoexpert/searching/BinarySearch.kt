package com.artishevskym.codinginterviews.solutions.algoexpert.searching

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function using the Binary Search algorithm to determine if the target integer is
 * contained in the array and should return its index if it is, otherwise -1.
 * @see <a href="https://www.algoexpert.io/questions/Binary%20Search">Binary Search</a>
 */
class BinarySearch {

    @Test
    fun testRecursive(){
        binarySearchRecursive(ARRAY, TARGET) shouldBeEqualTo OUTPUT
    }

    private fun binarySearchRecursive(array: List<Int>, target: Int): Int {
        return binarySearchHelperRecursive(array, target, 0, array.size - 1)
    }

    // recursively // can be better iteratively in context of space
    // O(log(n)) time | O(log(n)) space
    private fun binarySearchHelperRecursive(array: List<Int>, target: Int, left: Int, right: Int): Int {
        // stop condition
        if (left > right) return -1

        val middle = (left + right) / 2
        val potentialMatch = array[middle]

        if (target == potentialMatch) {
            // done, return index of target element
            return middle
        } else if (target < potentialMatch) {
            return binarySearchHelperRecursive(array, target, left, middle - 1)
        } else {
            return binarySearchHelperRecursive(array, target, middle + 1, right)
        }
    }

    @Test
    fun testIterative() {
        binarySearchIterative(ARRAY, TARGET) shouldBeEqualTo OUTPUT
    }

    private fun binarySearchIterative(array: List<Int>, target: Int): Int {
        return binarySearchHelperIterative(array, target, 0, array.size - 1)
    }

    // iteratively
    // O(log(n)) time | O(1) space
    private fun binarySearchHelperIterative(array: List<Int>, target: Int, leftIdx: Int, rightIdx: Int): Int {
        var left = leftIdx
        var right = rightIdx

        while (left <= right) {
            val middle = (left + right) / 2
            val potentialMatch = array[middle]

            if (target == potentialMatch) {
                return middle
            } else if (target < potentialMatch) {
                // go left
                right = middle - 1
            } else {
                // go right
                left = middle + 1
            }
        }

        // otherwise when left > right
        return -1
    }

    private companion object {
        private val ARRAY = listOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73)
        private const val TARGET = 33
        private const val OUTPUT = 3
    }
}