package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import kotlin.math.max

/**
 * Write a function to find the minimum rewards number following two rules:
 * 1. all students must receive at least one reward
 * 2. any given student must receive strictly more/fewer rewards than an adjacent student
 * @see <a href="https://www.algoexpert.io/questions/Min%20Rewards">Min Rewards</a>
 */
class MinRewards {

    @Test
    fun testNaiveSolution() {
        minRewardsNaive(SCORES) shouldBeEqualTo REWARDS_NUMBER
    }

    // naive solution
    // time: O(n^2) | space: O(n)
    private fun minRewardsNaive(scores: List<Int>): Int {
        val result = MutableList(scores.size) { 1 }

        for (i in 1 until scores.size) {
            var j = i - 1

            if (scores[i] > scores[j]) {
                // increase by 1
                result[i] = result[j] + 1
            } else {
                // fix backwards
                while (j >= 0 && scores[j] > scores[j + 1]) {
                    result[j] = max(result[j], result[j + 1] + 1)
                    j--
                }
            }
        }

        return result.sum()
    }

    @Test
    fun testLocalMinsSolution() {
        minRewardsLocalMins(SCORES) shouldBeEqualTo REWARDS_NUMBER
    }

    // time: O(n) | space: O(n)
    private fun minRewardsLocalMins(scores: List<Int>): Int {
        val result = MutableList(scores.size) { 1 }

        // note: we need only local mins and than expand to local max
        val localMinIndices = getLocalMinIndices(scores)
        for (localMinIdx in localMinIndices) {
            expandFromLocalMin(localMinIdx, scores, result)
        }

        return result.sum()
    }

    private fun getLocalMinIndices(array: List<Int>): List<Int> {
        if (array.size == 1) return listOf<Int>(0)

        val localMinIndices = mutableListOf<Int>()

        for (i in array.indices) {
            if (i == 0 && array[i] < array[i + 1]) localMinIndices.add(i)
            if (i == array.size - 1 && array[i] < array[i - 1]) localMinIndices.add(i)
            if (i == 0 || i == array.size - 1) continue

            if (array[i] < array[i + 1] && array[i] < array[i - 1]) localMinIndices.add(i)
        }

        return localMinIndices
    }

    private fun expandFromLocalMin(localMinIdx: Int, scores: List<Int>, result: MutableList<Int>) {
        var left = localMinIdx - 1
        while (left >= 0 && scores[left] > scores[left + 1]) {
            result[left] = max(result[left], result[left + 1] + 1)
            left--
        }

        var right = localMinIdx + 1
        while (right < scores.size && scores[right] > scores[right - 1]) {
            result[right] = result[right - 1] + 1
            right++
        }
    }

    @Test
    fun testOptimalSolution() {
        minRewardsOptimal(SCORES) shouldBeEqualTo REWARDS_NUMBER
    }

    // time: O(n) | space: (n)
    private fun minRewardsOptimal(scores: List<Int>): Int {
        val result = MutableList(scores.size) { 1 }

        for (i in 1 until scores.size) {
            if (scores[i] > scores[i - 1]) result[i] = result[i - 1] + 1
        }

        for (i in scores.size - 2 downTo 0) {
            if (scores[i] > scores[i + 1]) result[i] = max(result[i], result[i + 1] + 1)
        }

        return result.sum()
    }

    private companion object {
        private val SCORES = listOf(8, 4, 2, 1, 3, 6, 7, 9, 5)
        private const val REWARDS_NUMBER = 25
    }
}