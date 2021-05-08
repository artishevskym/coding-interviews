package com.artishevskym.codinginterviews.solutions.algoexpert.recursion

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function that takes in a special array and returns its product sum.
 * Note: product sum of a special array is the sum of its elements, where special arrays inside
 * it are summed themselves and then multiplied by their level of depth.
 *
 * @see <a href="https://www.algoexpert.io/questions/Product%20Sum">Product Sum</a>
 */
class ProductSum {

    @Test
    fun testRecursiveSolution() {
        productSum(INPUT) shouldBeEqualTo OUTPUT
    }

    private fun productSum(array: List<*>): Int {
        return productSumRecursiveHelper(array, 1)
    }

    // time: O(n) | space: O(d)
    private fun productSumRecursiveHelper(array: List<*>, depth: Int): Int {
        var sum = 0
        for (element in array) {
            if (element is Int) {
                sum += element
            } else if (element is List<*>) {
                sum += productSumRecursiveHelper(element, depth + 1)
            }
        }
        return sum * depth
    }

    private companion object {
        private val INPUT = listOf(5, 2, listOf(7, -1), 3, listOf(6, listOf(-13, 8), 4))
        private const val OUTPUT = 12 // 5+2+2*(7-1)+3*(6+3*(-13+8)+4)
    }
}