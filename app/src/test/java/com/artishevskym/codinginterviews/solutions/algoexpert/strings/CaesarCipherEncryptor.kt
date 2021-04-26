package com.artishevskym.codinginterviews.solutions.algoexpert.strings

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Implement Caesar Cipher Encryptor.
 * @see <a href="https://www.algoexpert.io/questions/Caesar%20Cipher%20Encryptor">Caesar Cipher Encryptor</a>
 */
class CaesarCipherEncryptor {

    @Test
    fun testSolutionAsciiCode() {
        caesarCipherEncryptorAscii(INPUT, KEY) shouldBeEqualTo OUTPUT
    }

    // O(n) time | O(n) space
    private fun caesarCipherEncryptorAscii(string: String, key: Int): String {
        val alphabetSize = 26
        val encodedString = mutableListOf<Char>()
        val normalizedKey = key % alphabetSize

        for (char in string) {
            encodedString.add(encode(char, normalizedKey))
        }

        return encodedString.joinToString("")
    }

    private fun encode(char: Char, key: Int): Char {
        val aCode = 97
        val zCode = 122
        val offset = aCode - 1
        val code = char.toInt() + key
        return if (code <= zCode) code.toChar() else (code % zCode + offset).toChar()
    }

    @Test
    fun testSolutionAlphabet() {
        caesarCipherEncryptorAlphabet(INPUT, KEY) shouldBeEqualTo OUTPUT
    }

    // O(n) time | O(n) space
    private fun caesarCipherEncryptorAlphabet(string: String, key: Int): String {
        val encodedString = mutableListOf<Char>()
        val alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray()
        val normalizedKey = key % alphabet.size

        for (char in string) {
            encodedString.add(encodeUsingAlphabet(char, normalizedKey, alphabet))
        }

        return encodedString.joinToString("")
    }

    private fun encodeUsingAlphabet(char: Char, key: Int, alphabet: CharArray): Char {
        val index = (alphabet.indexOf(char) + key) % alphabet.size
        return alphabet[index]
    }

    private companion object {
        private const val INPUT = "xyz"
        private const val KEY = 2
        private const val OUTPUT = "zab"
    }
}