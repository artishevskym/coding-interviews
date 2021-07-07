package com.artishevskym.codinginterviews.solutions.algoexpert.strings

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function that takes in a string and returns its longest palindromic substring.
 *
 * @see <a href="https://www.algoexpert.io/questions/Longest%20Palindromic%20Substring">Longest Palindromic Substring</a>
 */
class LongestPalindromicSubstring {

    @Test
    fun testBruteForceSolution() {
        longestPalindromicSubstringBruteForce(INPUT) shouldBeEqualTo OUTPUT
    }

    // brute force - generate all substrings
    // Time: O(n^3) | Space: O(n)
    private fun longestPalindromicSubstringBruteForce(string: String): String {
        var longest = ""

        for (i in 0 until string.length) {
            for (j in i until string.length) {
                val substring = string.substring(i, j+1)

                // update longest if needed
                if (substring.length > longest.length && isPalindrome(substring)) {
                    longest = substring
                }
            }
        }

        return longest
    }

    // Time: O(n) | Space: O(1)
    private fun isPalindrome(string: String): Boolean {
        var leftIdx = 0
        var rightIdx = string.length-1

        while (leftIdx < rightIdx) {
            if (string[leftIdx] != string[rightIdx]) return false
            leftIdx++
            rightIdx--
        }

        return true
    }

    @Test
    fun testOptimalSolution() {
        longestPalindromicSubstringOptimal(INPUT) shouldBeEqualTo OUTPUT
    }

    // optimal Time: O(n^2) | Space: O(n)
    private fun longestPalindromicSubstringOptimal(string: String): String {
        var longest = Pair(0, 1) // params for string.substring(startIdx, endIdx+1)

        // for each character search for odd- and even-length palindroms
        for (i in 1 until string.length) {
            val oddLength = getLongestPalindrome(string, i-1, i+1)
            val evenLength = getLongestPalindrome(string, i-1, i)

            val longer = if (oddLength.second - oddLength.first > evenLength.second - evenLength.first) oddLength else evenLength

            // update longest if needed
            if (longest.second - longest.first < longer.second - longer.first) {
                longest = longer
            }
        }

        return string.substring(longest.first, longest.second)
    }

    private fun getLongestPalindrome(string: String, leftIdx: Int, rightIdx: Int): Pair<Int, Int> {
        var left = leftIdx
        var right = rightIdx

        while (left >=0 && right < string.length) {
            if (string[left] != string[right]) break
            left--
            right++
        }

        return Pair(left+1, right) // palindrome in [left+1, right)
    }

    private companion object {
        private const val INPUT = "abaxyzzyxf"
        private const val OUTPUT = "xyzzyx"
    }
}