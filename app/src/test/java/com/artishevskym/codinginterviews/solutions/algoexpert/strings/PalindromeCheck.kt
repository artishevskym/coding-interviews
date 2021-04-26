package com.artishevskym.codinginterviews.solutions.algoexpert.strings

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Check if the string is a palindrome.
 * @see <a href="https://www.algoexpert.io/questions/Palindrome%20Check">Palindrome Check</a>
 */
class PalindromeCheck {

    @Test
    fun testStringConcatSolution() {
        isPalindromeStringConcat(INPUT) shouldBeEqualTo OUTPUT
    }

    // O(n^2) time | O(n) space
    private fun isPalindromeStringConcat(string: String = "", i: Int = 0): Boolean {
        var reversed = ""

        for (i in string.length-1 downTo 0) {
            reversed += string[i] // note: string concatenation is expensive
        }

        return string == reversed
    }

    @Test
    fun testCharConcatSolution() {
        isPalindromeCharConcat(INPUT) shouldBeEqualTo OUTPUT
    }

    // O(n) time | O(n) space
    private fun isPalindromeCharConcat(string: String = "", i: Int = 0): Boolean {
        val reversed = mutableListOf<Char>()

        for (i in string.length-1 downTo 0) {
            reversed.add(string[i])
        }

        return string == reversed.joinToString("")
    }

    @Test
    fun testRecursiveSolution() {
        isPalindromeRecursive(INPUT) shouldBeEqualTo OUTPUT
    }

    // O(n) time | O(n) space
    private fun isPalindromeRecursive(string: String = "", i: Int = 0): Boolean {
        val j = string.length-1-i

        if (i >= j) return true

        return string[i] == string[j] && isPalindromeRecursive(string, i+1)
    }

    @Test
    fun testOptimalSolution() {
        isPalindromeIterative(INPUT) shouldBeEqualTo OUTPUT
    }

    // O(n) time | O(1) space
    private fun isPalindromeIterative(string: String = "", i: Int = 0): Boolean {
        var leftIndex = 0
        var rightIndex = string.length-1

        while (leftIndex < rightIndex) {
            if (string[leftIndex] != string[rightIndex]) return false
            leftIndex++
            rightIndex--
        }

        return true
    }

    private companion object {
        private const val INPUT = "abcdcba"
        private const val OUTPUT = true
    }
}