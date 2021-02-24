package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Find the minimum amount of change (the minimum sum of money) that you cannot create
 * from given array of positive integers representing the values of coins.
 * @see <a href="https://www.algoexpert.io/questions/Non-Constructible%20Change">Non-Constructible Change</a>
 */
class NonConstructibleChange {

    @Test
    fun testSortingSolution() {
        nonConstructibleChange(COINS) shouldBeEqualTo EXPECTED_RESULT
    }

    // time: O(nlogn) | space: O(1)
    private fun nonConstructibleChange(coins: MutableList<Int>): Int {
        coins.sort()

        var currentChange = 0
        for (coin in coins) {
            if (coin > currentChange + 1) {
                return currentChange + 1 // value we cannot create
            }

            currentChange += coin
        }

        return currentChange + 1 // it's the next value we cannot create
    }

    private companion object {
        private val COINS = mutableListOf(5, 7, 1, 1, 2, 3, 22)
        private const val EXPECTED_RESULT = 20
    }
}