package com.artishevskym.codinginterviews.solutions.algoexpert.strings

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function that takes in a non-empty string and returns its run-length encoding.
 * @see <a href="https://www.algoexpert.io/questions/Run-Length%20Encoding">Run Length Encoding</a>
 */
class RunLengthEncoding {

    @Test
    fun testSolution() {
        runLengthEncoding(INPUT) shouldBeEqualTo OUTPUT
    }

    // time: O(n) | space: O(n)
    private fun runLengthEncoding(string: String): String {
        val encodedChars = mutableListOf<Char>()

        // edge case: 1-char length string
        if (string.length == 1) {
            return "1$string"
        }

        // main iteration: encode prevChar
        var runLength = 1
        for (i in 1 until string.length) {
            val currChar = string[i]
            val prevChar = string[i - 1]

            if (currChar != prevChar || runLength == 9) {
                encodedChars.add(runLength.toString()[0])
                encodedChars.add(prevChar)
                runLength = 0
            }

            runLength++
        }

        // edge case: handle last char
        encodedChars.add(runLength.toString()[0])
        encodedChars.add(string[string.length-1])

        return encodedChars.joinToString("")
    }



    private companion object {
        private const val INPUT = "AAAAAAAAAAAAABBCCCCDD"
        private const val OUTPUT = "9A4A2B4C2D"
    }
}