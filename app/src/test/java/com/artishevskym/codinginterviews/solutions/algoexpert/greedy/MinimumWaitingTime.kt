package com.artishevskym.codinginterviews.solutions.algoexpert.greedy

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function that returns the minimum amount of total waiting time for all of the queries.
 * @see <a href="https://www.algoexpert.io/questions/Minimum%20Waiting%20Time">Minimum Waiting Time</a>
 */
class MinimumWaitingTime {

    @Test
    fun testSolution() {
        minimumWaitingTime(INPUT) shouldBeEqualTo OUTPUT
    }

    // O(nlogn) time | O(1) space - where n is the number of queries
    private fun minimumWaitingTime(queries: MutableList<Int>): Int {
        // first sort queries in place
        queries.sort()

        // greedy choice - pick query with the smallest waiting time
        var totalWaitingTime = 0
        for (i in queries.indices) {
            val waitingTime = queries[i]
            val queriesLeft = queries.size - 1 - i
            totalWaitingTime += waitingTime * queriesLeft
        }
        return totalWaitingTime
    }

    private companion object {
        private val INPUT = mutableListOf(3, 2, 1, 2, 6)
        private const val OUTPUT = 17
    }
}