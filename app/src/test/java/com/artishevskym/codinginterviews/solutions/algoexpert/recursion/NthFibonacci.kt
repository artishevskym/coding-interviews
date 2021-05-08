package com.artishevskym.codinginterviews.solutions.algoexpert.recursion

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function that takes in an integer n and returns the n-th Fibonacci number.
 *
 * @see <a href="https://www.algoexpert.io/questions/Nth%20Fibonacci">Nth Fibonacci</a>
 */
class NthFibonacci {

    @Test
    fun testRecursiveSolution() {
        getNthFibRecursive(INPUT) shouldBeEqualTo OUTPUT
    }

    // O(n^2) time / O(n) space
    private fun getNthFibRecursive(n: Int): Int {
        when (n) {
            2 -> return 1 // F2
            1 -> return 0 // F1
        }
        return getNthFibRecursive(n - 1) + getNthFibRecursive(n - 2)
    }

    @Test
    fun testSolutionWithCaching() {
        getNthFibWithCaching(INPUT) shouldBeEqualTo OUTPUT
    }

    // O(n) time / O(n) space
    private fun getNthFibWithCaching(n: Int): Int {
        // with storing calculated values
        val cache = mutableMapOf<Int, Int>(1 to 0, 2 to 1)
        return getNthFibWithCaching(n, cache)
    }

    private fun getNthFibWithCaching(n: Int, cache: MutableMap<Int, Int>): Int {
        if (!cache.containsKey(n)) {
            cache[n] = getNthFibWithCaching(n - 1, cache) + getNthFibWithCaching(n - 2, cache)
        }

        return cache[n]!!
    }

    @Test
    fun testSolutionWithOptimizedCaching() {
        getNthFibOptimal(INPUT) shouldBeEqualTo OUTPUT
    }

    // time: O(n) | space: O(1)
    private fun getNthFibOptimal(n: Int): Int {
        // optimized storing - keep only two last fib numbers
        var lastTwoFibs = Pair(0, 1)
        var nextIndex = 3

        while (nextIndex <= n) {
            val nextFib = lastTwoFibs.first + lastTwoFibs.second
            lastTwoFibs = Pair(lastTwoFibs.second, nextFib)
            nextIndex++
        }

        return if (n > 1) lastTwoFibs.second else lastTwoFibs.first
    }

    private companion object {
        private const val INPUT = 6
        private const val OUTPUT = 5
    }
}