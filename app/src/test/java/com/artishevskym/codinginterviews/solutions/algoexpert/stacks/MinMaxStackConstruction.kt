package com.artishevskym.codinginterviews.solutions.algoexpert.stacks

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import kotlin.math.max
import kotlin.math.min

/**
 * Implement class for a Min Max Stack
 * - pushing and popping values on and off the stack
 * - peeking at the value at the top of the stack
 * - getting both the minimum and the maximum values in the stack at any given point in time
 *
 * @see <a href="https://www.algoexpert.io/questions/Min%20Max%20Stack%20Construction">Min Max Stack Construction</a>
 */
class MinMaxStackConstruction {

    @Test
    fun testSolution() {
        val sut = MinMaxStack()

        sut.push(5)
        sut.peek() shouldBeEqualTo 5
        sut.getMin() shouldBeEqualTo 5
        sut.getMax() shouldBeEqualTo 5

        sut.push(7)
        sut.peek() shouldBeEqualTo 7
        sut.getMin() shouldBeEqualTo 5
        sut.getMax() shouldBeEqualTo 7

        sut.push(2)
        sut.peek() shouldBeEqualTo 2
        sut.getMin() shouldBeEqualTo 2
        sut.getMax() shouldBeEqualTo 7

        sut.pop()
        sut.peek() shouldBeEqualTo 7
        sut.getMin() shouldBeEqualTo 5
        sut.getMax() shouldBeEqualTo 7

        sut.pop()
        sut.peek() shouldBeEqualTo 5
        sut.getMin() shouldBeEqualTo 5
        sut.getMax() shouldBeEqualTo 5

        sut.pop()
        sut.peek() shouldBeEqualTo null
        sut.getMin() shouldBeEqualTo null
        sut.getMax() shouldBeEqualTo null
    }

    open class MinMaxStack() {
        private val stack = mutableListOf<Int>()

        // take snapshot of min/max for each element in the stack
        private val minMaxStack = mutableListOf<Map<String, Int>>()

        // time: O(1) | space: O(1)
        fun peek(): Int? {
            val lastIdx = stack.size - 1
            return if (lastIdx < 0) null else stack[lastIdx] // LIFO
        }

        // time: O(1) | space: O(1)
        fun pop(): Int? {
            val lastIdx = stack.size - 1
            minMaxStack.removeAt(lastIdx)
            return if (lastIdx < 0) null else stack.removeAt(lastIdx)
        }

        // time: O(1) | space: O(1)
        fun push(number: Int) {
            val newMinMax = mutableMapOf<String, Int>("min" to number, "max" to number)
            if (minMaxStack.size > 0) {
                val lastMinMax = minMaxStack[minMaxStack.size - 1]
                newMinMax["min"] = min(lastMinMax["min"]!!, number)
                newMinMax["max"] = max(lastMinMax["max"]!!, number)
            }
            minMaxStack.add(newMinMax)
            stack.add(number)
        }

        // time: O(1) | space: O(1)
        fun getMin(): Int? {
            val lastIdx = minMaxStack.size - 1
            return if (lastIdx < 0) return null else minMaxStack[lastIdx]["min"]
        }

        // time: O(1) | space: O(1)
        fun getMax(): Int? {
            val lastIdx = minMaxStack.size - 1
            return if (lastIdx < 0) return null else minMaxStack[lastIdx]["max"]
        }
    }

}