package com.artishevskym.codinginterviews.solutions.algoexpert.strings

import android.view.ViewDebug
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function that determines if you can generate the document using the
 * available characters.
 * @see <a href="https://www.algoexpert.io/questions/Generate%20Document">Generate Document</a>
 */
class GenerateDocument {

    @Test
    fun testBruteForceSolution() {
        generateDocumentBruteForce(CHARACTERS, DOCUMENT) shouldBeEqualTo OUTPUT
    }

    // O(m * (n+m)) time | O(1) space; n - number of characters, m - length of document
    private fun generateDocumentBruteForce(characters: String, document: String): Boolean {
        for (char in document) {
            val frequencyInDocument = countCharFrequency(char, document)
            val frequencyInCharacters = countCharFrequency(char, characters)

            if (frequencyInDocument > frequencyInCharacters) return false
        }

        return true
    }

    private fun countCharFrequency(char: Char, string: String): Int {
        var frequency = 0
        for (c in string) {
            if (c == char) frequency++
        }
        return frequency
    }

    @Test
    fun testBetterSolutionCountingForUniqueCharacters() {
        generateDocumentUniqueChars(CHARACTERS, DOCUMENT) shouldBeEqualTo OUTPUT
    }

    // O(c * (n+m)) time | O(1) space; n - number of characters, m - length of document
    // c - number of unique characters in document
    private fun generateDocumentUniqueChars(characters: String, document: String): Boolean {
        val alreadyChecked = mutableSetOf<Char>()

        for (char in document) {
            if (char in alreadyChecked) continue

            val frequencyInDocument = countCharFrequency(char, document)
            val frequencyInCharacters = countCharFrequency(char, characters)

            if (frequencyInDocument > frequencyInCharacters) return false

            alreadyChecked.add(char)
        }

        return true
    }

    @Test
    fun testOptimalSolution() {
        generateDocumentOptimal(CHARACTERS, DOCUMENT) shouldBeEqualTo OUTPUT
    }

    // time: O(n+m) | space: O(c); n - characters, m - document, c - unique characters count
    private fun generateDocumentOptimal(characters: String, document: String): Boolean {
        val charCounts = mutableMapOf<Char, Int>()

        for (char in characters) {
            if (!(char in charCounts)) charCounts[char] = 0
            charCounts[char] = charCounts[char]!! + 1
        }

        for (char in document) {
            if (!(char in charCounts) || charCounts[char] == 0) return false

            charCounts[char] = charCounts[char]!! - 1
        }

        return true
    }

    private companion object {
        private const val CHARACTERS = "Bste!hetsi ogEAxpelrt x "
        private const val DOCUMENT = "AlgoExpert is the Best!"
        private const val OUTPUT = true
    }
}