package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function to transform input array in the output where output[i] is equal to the product
 * of every number in the input array other than input[i].
 * @see <a href="https://www.algoexpert.io/questions/Array%20Of%20Products">Array Of Products</a>
 */
class ArrayOfProducts {

    @Test
    fun testBruteForceSolution() {
        arrayOfProductsUsingBruteForce(SAMPLE_INPUT) shouldBeEqualTo SAMPLE_OUTPUT
    }

    // time: O(n^2) | space: O(n)
    private fun arrayOfProductsUsingBruteForce(array: List<Int>): List<Int> {
        val products = MutableList(array.size) { 1 }

        for (i in array.indices) {
            var currentProduct = 1

            for (j in array.indices) {
                if (i != j) currentProduct *= array[j]
            }

            products[i] = currentProduct
        }

        return products
    }

    @Test
    fun testOptimalSolution3N() {
        arrayOfProductsUsingOptimal3N(SAMPLE_INPUT) shouldBeEqualTo SAMPLE_OUTPUT
    }

    // time: O(n) | space: O(n)
    private fun arrayOfProductsUsingOptimal3N(array: List<Int>): List<Int> {
        val products = MutableList(array.size) { 1 }
        val leftProducts = MutableList(array.size) { 1 }
        val rightProducts = MutableList(array.size) { 1 }

        // store left products
        var leftCurrentProduct = 1
        for (i in array.indices) {
            leftProducts[i] = leftCurrentProduct
            leftCurrentProduct *= array[i]
        }

        // store right products
        var rightCurrentProduct = 1
        for (i in array.size-1 downTo 0) {
            rightProducts[i] = rightCurrentProduct
            rightCurrentProduct *= array[i]
        }

        // calculate products
        for (i in array.indices) {
            products[i] = leftProducts[i] * rightProducts[i]
        }

        return products
    }

    @Test
    fun testOptimalSolution1N() {
        arrayOfProductsUsingOptimal1N(SAMPLE_INPUT) shouldBeEqualTo SAMPLE_OUTPUT
    }

    // time: O(n) | space: O(n)
    private fun arrayOfProductsUsingOptimal1N(array: List<Int>): List<Int> {
        val products = MutableList(array.size) { 1 }

        // store left products
        var leftCurrentProduct = 1
        for (i in array.indices) {
            products[i] = leftCurrentProduct
            leftCurrentProduct *= array[i]
        }

        // update with right products
        var rightCurrentProduct = 1
        for (i in array.size-1 downTo 0) {
            products[i] *= rightCurrentProduct
            rightCurrentProduct *= array[i]
        }

        return products
    }

    private companion object {
        private val SAMPLE_INPUT = listOf(5, 1, 4, 2)
        private val SAMPLE_OUTPUT = listOf(8, 40, 10, 20)
    }
}