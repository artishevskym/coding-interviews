package com.artishevskym.codinginterviews.solutions.algoexpert.bst

import com.google.gson.Gson
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import kotlin.math.max

/**
 * Write a function that takes in a non-empty sorted array of distinct integers
 * and constructs a BST, and returns the root of the BST.
 *
 * Note: function should minimize the height of the BST.
 *
 * @see <a href="https://www.algoexpert.io/questions/Min%20Height%20BST">Min Height BST</a>
 */
class MinHeightBST {

    data class BstJson(
        val root: String,
        val nodes: List<BST.Companion.NodeJson>
    )

    open class BST(value: Int) {
        var value = value
        var left: BST? = null
        var right: BST? = null

        constructor(value: Int, left: BST?, right: BST?) : this(value) {
            this.left = left
            this.right = right
        }

        fun insert(value: Int) {
            if (value < this.value) {
                if (this.left == null) {
                    this.left = BST(value)
                } else {
                    this.left!!.insert(value)
                }
            } else {
                if (this.right == null) {
                    this.right = BST(value)
                } else {
                    this.right!!.insert(value)
                }
            }
        }

        companion object {
            data class NodeJson(
                val id: String,
                val left: String?,
                val right: String?,
                val value: Int
            )

            fun create(root: String, nodes: List<NodeJson>): BST {
                val node = nodes.first { it.id == root }

                var leftNode: BST? = null
                if (node.left != null) {
                    leftNode = create(node.left, nodes)
                }

                var rightNode: BST? = null
                if (node.right != null) {
                    rightNode = create(node.right, nodes)
                }

                return BST(node.value, leftNode, rightNode)
            }

            fun maxDepth(node: BST?): Int {
                if (node == null) {
                    return 0
                } else {
                    val leftDepth = maxDepth(node.left)
                    val rightDepth = maxDepth(node.right)
                    return max(leftDepth, rightDepth) + 1
                }
            }
        }
    }

    private lateinit var root: BST

    @Before
    fun setup() {
        val json = Gson().fromJson(BST_IN_JSON, BstJson::class.java)
        root = BST.create(json.root, json.nodes)
    }

    @Test
    fun testBruteForceSolution() {
        val bst = minHeightBstBruteForce(SAMPLE_INPUT)
        bst.value shouldBeEqualTo SAMPLE_OUTPUT_VALUE
        BST.maxDepth(bst) shouldBeEqualTo SAMPLE_OUTPUT_MIN_HEIGHT
    }

    // brute-force - convert sorted array to BST by picking mid element
    // Time: O(nlog(n)) | Space: O(n)
    private fun minHeightBstBruteForce(array: List<Int>): BST {
        return minHeightBstHelperBruteForce(array, null, 0, array.size - 1)!!
    }

    private fun minHeightBstHelperBruteForce(array: List<Int>, node: BST?, startIdx: Int, endIdx: Int): BST? {
        if (endIdx < startIdx) return null // stop condition

        var root: BST? = node
        val midIdx = (startIdx + endIdx) / 2
        val midValue = array[midIdx]

        if (root != null) {
            root.insert(midValue) // O(log(n))
        } else {
            root = BST(midValue)
        }

        // construct bst for left part
        minHeightBstHelperBruteForce(array, root, startIdx, midIdx - 1)
        // construct bst for right part
        minHeightBstHelperBruteForce(array, root, midIdx + 1, endIdx)

        return root
    }

    @Test
    fun testManualNodeConstructSolution() {
        val bst = minHeightBstManualConstruct(SAMPLE_INPUT)
        bst.value shouldBeEqualTo SAMPLE_OUTPUT_VALUE
        BST.maxDepth(bst) shouldBeEqualTo SAMPLE_OUTPUT_MIN_HEIGHT
    }

    // improved using manual bst construction
    // Time: O(n) | Space: O(n)
    private fun minHeightBstManualConstruct(array: List<Int>): BST {
        return minHeightBstHelperManualConstruct(array, null, 0, array.size-1)!!
    }

    private fun minHeightBstHelperManualConstruct(array: List<Int>, bst: BST?, startIdx: Int, endIdx: Int): BST? {
        if (endIdx < startIdx) return null // stop condition

        var root: BST? = bst
        val midIdx = (startIdx + endIdx) / 2
        val midValue = array[midIdx]

        // manual construction of new node with new value to insert
        val node = BST(midValue)
        if (root == null) {
            root = node
        } else {
            if (midValue < root.value) {
                root.left = node
                root = root.left
            } else {
                root.right = node
                root = root.right
            }
        }

        // construct bst for left part
        minHeightBstHelperManualConstruct(array, root, startIdx, midIdx-1)
        // construct bst for right part
        minHeightBstHelperManualConstruct(array, root, midIdx+1, endIdx)

        return root
    }

    @Test
    fun testOptimalSolution() {
        val bst = minHeightBstOptimal(SAMPLE_INPUT)
        bst.value shouldBeEqualTo SAMPLE_OUTPUT_VALUE
        BST.maxDepth(bst) shouldBeEqualTo SAMPLE_OUTPUT_MIN_HEIGHT
    }

    // optimal using manual bst construction
    // Time: O(n) | Space: O(n)
    private fun minHeightBstOptimal(array: List<Int>): BST {
        return minHeightBstHelperOptimal(array, 0, array.size-1)!!
    }

    private fun minHeightBstHelperOptimal(array: List<Int>, startIdx: Int, endIdx: Int): BST? {
        if (endIdx < startIdx) return null // stop condition

        val midIdx = (startIdx + endIdx) / 2
        val midValue = array[midIdx]

        // manual construction of new node with new value to insert
        val node = BST(midValue)
        // construct bst for left part
        node.left = minHeightBstHelperOptimal(array, startIdx, midIdx-1)
        // construct bst for right part
        node.right = minHeightBstHelperOptimal(array, midIdx+1, endIdx)

        return node
    }

    private companion object {
        private val SAMPLE_INPUT = listOf(1, 2, 5, 7, 10, 13, 14, 15, 22)
        private const val SAMPLE_OUTPUT_VALUE = 10
        private const val SAMPLE_OUTPUT_MIN_HEIGHT = 4
        private const val BST_IN_JSON =
            """
{
    "root": "10",
    "nodes": [
        {"id": "10",  "left": "5",  "right": "15",  "value": 10},
        {"id": "15",  "left": "13", "right": "22",  "value": 15},
        {"id": "22",  "left": null, "right": null,  "value": 22},
        {"id": "13",  "left": null, "right": "14",  "value": 13},
        {"id": "14",  "left": null, "right": null,  "value": 14},
        {"id": "5",   "left": "2",  "right": "5-2", "value": 5},
        {"id": "5-2", "left": null, "right": null,  "value": 5},
        {"id": "2",   "left": "1",  "right": null,  "value": 2},
        {"id": "1",   "left": null, "right": null,  "value": 1}
    ]
}
            """
    }
}