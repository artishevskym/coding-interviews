package com.artishevskym.codinginterviews.solutions.algoexpert.stacks

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import java.util.Stack

/**
 * Check if string made up of brackets: ()[]{} and other optional characters is balanced with
 * regards to brackets.
 *
 * @see <a href="https://www.algoexpert.io/questions/Balanced%20Brackets">Balanced Brackets</a>
 */
class BalancedBrackets {

    @Test
    fun testSolutionUsingStack() {
        balancedBrackets(INPUT) shouldBeEqualTo OUTPUT
    }

    // time: O(n) | space: O(n)
    private fun balancedBrackets(str: String): Boolean {
        val stack = Stack<Char>()

        val openingBrackets = "([{"
        val closingBrackets = ")]}"

        val matchingOpening = mapOf(
            ')' to '(',
            ']' to '[',
            '}' to '{'
        )

        for (char in str) {
            if (openingBrackets.contains(char)) {
                stack.add(char)
            } else if (closingBrackets.contains(char)) {
                if (stack.size == 0) return false

                if (stack.peek() == matchingOpening[char]) {
                    stack.pop()
                } else {
                    return false
                }
            }
        }

        // empty stack means that brackets are balanced
        return stack.size == 0
    }

    private companion object {
        private const val INPUT = "([])(){}(())()()"
        private const val OUTPUT = true
    }
}