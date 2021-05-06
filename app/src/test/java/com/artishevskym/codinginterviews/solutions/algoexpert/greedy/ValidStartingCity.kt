package com.artishevskym.codinginterviews.solutions.algoexpert.greedy

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function that returns the index of valid starting city.
 * Note: cities are laid out in a circle
 * @see <a href="https://www.algoexpert.io/questions/Valid%20Starting%20City">Valid Starting City</a>
 */
class ValidStartingCity {

    @Test
    fun testBruteForce() {
        validStartingCityBruteForce(DISTANCES, FUEL, MPG) shouldBeEqualTo OUTPUT
    }

    // brute force; time: O(n^2) | space: O(1)
    private fun validStartingCityBruteForce(distances: List<Int>, fuel: List<Int>, mpg: Int): Int {
        val n = distances.size

        for (startingCity in 0 until n) {
            var remainingMiles = 0

            for (currentCity in startingCity until startingCity + n) {
                if (remainingMiles < 0) continue // it is not valid starting city

                val i = currentCity % n
                remainingMiles += fuel[i] * mpg - distances[i]
            }

            if (remainingMiles >= 0) return startingCity
        }

        // otherwise incorrect input
        return -1
    }

    @Test
    fun testOptimal() {
        validStartingCity(DISTANCES, FUEL, MPG) shouldBeEqualTo OUTPUT
    }

    // time: O(n) | space: O(1)
    private fun validStartingCity(distances: List<Int>, fuel: List<Int>, mpg: Int): Int {
        val n = distances.size
        var remainingMiles = 0
        var startingCityIndex = 0
        var remainingMilesAtStartingCity = 0

        // one iteration - searching for minimum remaining miles when travelling
        for (i in 1 until n) {
            val distanceFromPrev = distances[i-1]
            val fuelFromPrev = fuel[i-1]
            remainingMiles += fuelFromPrev * mpg - distanceFromPrev

            // greedy lookout for minimum - update starting city index
            if (remainingMiles < remainingMilesAtStartingCity) {
                startingCityIndex = i
                remainingMilesAtStartingCity = remainingMiles
            }
        }

        return startingCityIndex
    }

    private companion object {
        private val DISTANCES = listOf(5, 25, 15, 10, 15)
        private val FUEL = listOf(1, 2, 1, 0, 3)
        private const val MPG = 10
        private const val OUTPUT = 4
    }
}