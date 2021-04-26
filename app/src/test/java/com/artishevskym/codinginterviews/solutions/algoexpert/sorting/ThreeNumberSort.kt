package com.artishevskym.codinginterviews.solutions.algoexpert.sorting

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function that sorts the first array according to the desired order in the second array.
 * @see <a href="https://www.algoexpert.io/questions/Three%20Number%20Sort">Three Number Sort</a>
 */
class ThreeNumberSort {

    @Test
    fun testSolutionCounting() {
        threeNumberSortByCount(INPUT, ORDER) shouldBeEqualTo OUTPUT
    }

    // time: O(n) | space: O(1)
    private fun threeNumberSortByCount(array: MutableList<Int>, order: List<Int>): List<Int> {
        val valueCounts = mutableListOf(0, 0, 0)

        for (element in array) {
            val orderIdx = order.indexOf(element)
            valueCounts[orderIdx]++
        }

        for (i in 0 until 3) {
            val value = order[i]
            val count = valueCounts[i]

            val elementsBeforeCount = valueCounts.subList(0, i).sum()
            for (n in 0 until count) {
                val idx = elementsBeforeCount + n
                array[idx] = value
            }
        }

        return array
    }

    @Test
    fun testSolutionIter() {
        threeNumberSortIter(INPUT, ORDER) shouldBeEqualTo OUTPUT
    }

    // time: O(n) | space: O(1)
    private fun threeNumberSortIter(array: MutableList<Int>, order: List<Int>): List<Int> {
        var firstIdx = 0
        for (idx in array.indices) {
            if (array[idx] == order[0]) {
                swap(firstIdx, idx, array)
                firstIdx++
            }
        }

        var thirdIdx = array.size-1
        for (idx in array.size-1 downTo 0) {
            if (array[idx] == order[2]) {
                swap(thirdIdx, idx, array)
                thirdIdx--
            }
        }

        return array
    }

    private fun swap(i: Int, j: Int, array: MutableList<Int>) {
        val temp = array[i]
        array[i] = array[j]
        array[j] = temp
    }

    @Test
    fun testOptimalSolution() {
        threeNumberSortOptimal(INPUT, ORDER) shouldBeEqualTo OUTPUT
    }

    // time: O(n) | space: O(1)
    private fun threeNumberSortOptimal(array: MutableList<Int>, order: List<Int>): List<Int> {
        var firstIndex = 0
        var secondIndex= 0
        var thirdIndex = array.size-1

        while (secondIndex <= thirdIndex) {
            val value = array[secondIndex]

            when (value) {
                order[0] -> {
                    // swap to first values range
                    swap(firstIndex, secondIndex, array)
                    firstIndex++
                    secondIndex++
                }
                order[1] -> {
                    // no swapping, continue to next number
                    secondIndex++
                }
                else -> {
                    // swap to third values range
                    swap(secondIndex, thirdIndex, array)
                    thirdIndex--
                }
            }
        }

        return array
    }

    private companion object {
        private val INPUT = mutableListOf(1, 0, 0, -1, -1, 0, 1, 1)
        private val ORDER = listOf(0, 1, -1)
        private val OUTPUT = listOf(0, 0, 0, 1, 1, 1, -1, -1)
    }
}