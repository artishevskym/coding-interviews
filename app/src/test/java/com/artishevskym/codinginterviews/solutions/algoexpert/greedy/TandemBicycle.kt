package com.artishevskym.codinginterviews.solutions.algoexpert.greedy

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import kotlin.math.max

/**
 * Write a function that returns the maximum possible total speed or the minimum possible total
 * speed of all of the tandem bicycles being ridden based on an input parameter 'fastest'.
 * If fastest=true, return the maximum possible total speed; otherwise the minimum total speed.
 * @see <a href="https://www.algoexpert.io/questions/Tandem%20Bicycle">Tandem Bicycle</a>
 */
class TandemBicycle {

    @Test
    fun testSolution() {
        tandemBicycle(RED_SHIRT_SPEEDS, BLUE_SHIRT_SPEEDS, FASTEST) shouldBeEqualTo OUTPUT
    }

    // time: O(nlogn) | space: O(1)
    private fun tandemBicycle(redShirtSpeeds: MutableList<Int>, blueShirtSpeeds: MutableList<Int>, fastest: Boolean): Int {
        redShirtSpeeds.sort()
        if (!fastest) blueShirtSpeeds.sortDescending() else blueShirtSpeeds.sort()

        var totalSpeed = 0

        for (i in redShirtSpeeds.indices) {
            val rider1 = redShirtSpeeds[i]
            val rider2 = blueShirtSpeeds[blueShirtSpeeds.size-1-i]
            totalSpeed += max(rider1, rider2)
        }

        return totalSpeed
    }

    private companion object {
        private val RED_SHIRT_SPEEDS = mutableListOf(5,5,3,9,2)
        private val BLUE_SHIRT_SPEEDS = mutableListOf(3,6,7,2,1)
        private const val FASTEST = true
        private const val OUTPUT = 32
    }
}