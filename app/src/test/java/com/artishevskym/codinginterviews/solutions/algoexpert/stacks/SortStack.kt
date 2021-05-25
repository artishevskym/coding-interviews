package com.artishevskym.codinginterviews.solutions.algoexpert.stacks

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write function that takes in an integers array representing a stack, recursively sort the
 * stack in place and returns it.
 *
 * @see <a href="https://www.algoexpert.io/questions/Sort%20Stack">Sort Stack</a>
 */
class SortStack {

    @Test
    fun testSolution() {
        sortStack(STACK) shouldBeEqualTo OUTPUT
    }

    // time: O(n^2) | space: O(n)
    private fun sortStack(stack: MutableList<Int>): MutableList<Int> {
        if (stack.size == 0) return stack

        // use recurrence
        val value = stack.removeAt(stack.size-1)
        sortStack(stack)
        insertInSortedStack(stack, value)

        return stack
    }

    private fun insertInSortedStack(stack: MutableList<Int>, value: Int) {
        // empty or greater then top value - push value to stack and finish
        if (stack.size == 0 || stack[stack.size-1] <= value) {
            stack.add(value)
            return
        }

        // remove top, recursive call and add top value in sorted order
        val top = stack.removeAt(stack.size-1)
        insertInSortedStack(stack, value)
        stack.add(top)
    }

    private companion object {
        private val STACK = mutableListOf(-5, 2, -2, 4, 3, 1)
        private val OUTPUT = mutableListOf(-5, -2, 1, 2, 3, 4)
    }
}