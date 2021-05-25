package com.artishevskym.codinginterviews.solutions.algoexpert.stacks

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Writing mapping function that finds the next element in the input array that's
 * greater than the element at that index in the input array.
 * @see <a href="https://www.algoexpert.io/questions/Next%20Greater%20Element">Next Greater Element</a>
 */
class NextGreaterElement {

    @Test
    fun testSolutionUsingStackFromLeftToRight() {
        nextGreaterElementLeftToRight(INPUT) shouldBeEqualTo OUTPUT
    }

    // time: O(n) | space: O(n)
    private fun nextGreaterElementLeftToRight(array: MutableList<Int>): MutableList<Int> {

        val result = MutableList(array.size) { -1 }
        val stack = mutableListOf<Int>()


        for (i in 0 until 2 * array.size) {
            val index = i % array.size // circular array

            while (stack.size > 0 && array[stack[stack.size - 1]] < array[index]) {
                // update results for indices in stack when next greater value found
                val peek = stack.removeAt(stack.size - 1)
                result[peek] = array[index]
            }

            // collect index in stack
            stack.add(index)
        }

        return result
    }

    @Test
    fun testSolutionUsingStackFromRightToLeft() {
        nextGreaterElementRightToLeft(INPUT) shouldBeEqualTo OUTPUT
    }

    // time: O(n) | space: O(n)
    private fun nextGreaterElementRightToLeft(array: MutableList<Int>): MutableList<Int> {

        val result = MutableList(array.size) { -1 }
        val stack = mutableListOf<Int>()

        // traverse from right to left
        for (i in 2 * array.size - 1 downTo 0) {
            val index = i % array.size // circular array

            while (stack.size > 0) {
                if (stack[stack.size - 1] <= array[index]) {
                    stack.removeAt(stack.size - 1)
                } else {
                    // peek - our next greater number found
                    result[index] = stack[stack.size - 1]
                    break
                }
            }

            // store value in stack
            stack.add(array[index])
        }

        return result
    }

    private companion object {
        private val INPUT = mutableListOf(2, 5, -3, -4, 6, 7, 2)
        private val OUTPUT = mutableListOf(5, 6, 6, 6, 7, -1, 5)
    }
}