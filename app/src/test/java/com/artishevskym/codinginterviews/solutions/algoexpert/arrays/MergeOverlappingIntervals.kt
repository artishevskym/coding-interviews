package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import kotlin.math.max

/**
 * Write a function to merge any overlapping intervals.
 * @see <a href="https://www.algoexpert.io/questions/Merge%20Overlapping%20Intervals">Merge Overlapping Intervals</a>
 */
class MergeOverlappingIntervals {

    @Test
    fun testSolution() {
        mergeOverlappingIntervals(SAMPLE_INPUT) shouldBeEqualTo SAMPLE_OUTPUT
    }

    // time: O(nlog(n)) | space: O(n)
    private fun mergeOverlappingIntervals(intervals: List<List<Int>>): List<List<Int>> {
        // sort intervals by first starting values
        val comparator = Comparator<List<Int>> { a, b -> a[0].compareTo(b[0]) }
        var sortedIntervals = intervals.toMutableList()
            .sortedWith(comparator)
            .map() { it.toMutableList() }

        val mergedIntervals = mutableListOf<MutableList<Int>>()

        // initiate with first interval
        var currInterval = sortedIntervals[0]
        mergedIntervals.add(currInterval)

        // iterate through intervals
        for (nextInterval in sortedIntervals) {
            val (_, currIntervalEnd) = currInterval
            val (nextIntervalStart, nextIntervalEnd) = nextInterval

            if (currIntervalEnd >= nextIntervalStart) {
                // merge intervals
                currInterval[1] = max(currIntervalEnd, nextIntervalEnd)
            } else {
                // add next interval
                currInterval = nextInterval
                mergedIntervals.add(currInterval)
            }
        }

        return mergedIntervals
    }

    private companion object {
        private val SAMPLE_INPUT =
            listOf(listOf(1, 2), listOf(3, 5), listOf(4, 7), listOf(6, 8), listOf(9, 10))
        private val SAMPLE_OUTPUT = listOf(listOf(1, 2), listOf(3, 8), listOf(9, 10))
    }
}