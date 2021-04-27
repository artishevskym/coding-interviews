package com.artishevskym.codinginterviews.solutions.algoexpert.strings

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContainSame
import org.junit.Test
import kotlin.math.min

/**
 * Write function to generate valid IP addresses from the inpute string.
 * @see <a href="https://www.algoexpert.io/questions/Valid%20IP%20Addresses">Valid IP Addresses</a>
 */
class ValidIpAddresses {

    @Test
    fun testSolution() {
        validIPAddresses(INPUT) shouldContainSame OUTPUT
    }

    // time: O(1) - limited number of IP addresses possible to build; space: O(1)
    private fun validIPAddresses(string: String): List<String> {
        val found = mutableListOf<String>()

        // part's length up to 3
        // looking for first part
        for (i in 1 until min(string.length,4)) {
            val currParts = mutableListOf("","","","")
            currParts[0] = string.substring(0,i)
            if (!isValid(currParts[0])) continue

            // looking for second part
            for (j in i+1 until i+min(string.length-i,4)) {
                currParts[1] = string.substring(i,j)
                if (!isValid(currParts[1])) continue

                // looking for third and fourth parts
                for (k in j+1 until j+min(string.length-j,4)) {
                    currParts[2] = string.substring(j,k)
                    currParts[3] = string.substring(k)

                    if (isValid(currParts[2]) && isValid(currParts[3])) {
                        found.add(currParts.joinToString("."))
                    }
                }
            }
        }

        return found
    }

    private fun isValid(part: String): Boolean {
        val partAsInt = part.toInt() // removing leading 0's
        if (partAsInt > 255) return false

        // check for leading 0's
        return part.length == partAsInt.toString().length
    }

    private companion object {
        private const val INPUT = "1921680"
        private val OUTPUT = listOf(
            "1.9.216.80",
            "1.92.16.80",
            "1.92.168.0",
            "19.2.16.80",
            "19.2.168.0",
            "19.21.6.80",
            "19.21.68.0",
            "19.216.8.0",
            "192.1.6.80",
            "192.1.68.0",
            "192.16.8.0"
        )
    }
}