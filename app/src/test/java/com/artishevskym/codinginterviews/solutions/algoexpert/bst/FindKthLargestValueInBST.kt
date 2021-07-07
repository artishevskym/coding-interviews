package com.artishevskym.codinginterviews.solutions.algoexpert.bst

import com.google.gson.Gson
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

/**
 * Write a function that takes in a BST and a positive integer k and
 * returns the kth largest integer contained in the BST.
 *
 * @see <a href="https://www.algoexpert.io/questions/Find%20Kth%20Largest%20Value%20In%20BST">Find Kth Largest Value In BST</a>
 */
class FindKthLargestValueInBST {
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
        }
    }

    private lateinit var root: BST

    @Before
    fun setup() {
        val json = Gson().fromJson(BST_IN_JSON, BstJson::class.java)
        root = BST.create(json.root, json.nodes)
    }

    @Test
    fun testInOrderTraversal() {
        findKthLargestValueInBstInOrderTraversal(root, K) shouldBeEqualTo EXPECTED_VALUE
    }

    // brute-force (BST == sorted distinct values)
    // Time: O(n) | Space: O(n)
    private fun findKthLargestValueInBstInOrderTraversal(tree: BST, k: Int): Int {
        val sortedValues = mutableListOf<Int>()

        inOrderTraverse(tree, sortedValues)

        val kthLargestIdx = sortedValues.size - k
        return sortedValues[kthLargestIdx]
    }

    private fun inOrderTraverse(node: BST?, sortedValues: MutableList<Int>) {
        if (node == null) return

        inOrderTraverse(node.left, sortedValues)
        sortedValues.add(node.value)
        inOrderTraverse(node.right, sortedValues)
    }

    @Test
    fun testReverseInOrderTraversal() {
        findKthLargestValueInBstReverseInOrderTraversal(root, K) shouldBeEqualTo EXPECTED_VALUE
    }

    data class VisitedNode(
        var count: Int,
        var value: Int
    )

    // optimal Time: O(h+k) | Space: O(h)
    private fun findKthLargestValueInBstReverseInOrderTraversal(tree: BST, k: Int): Int {
        val latestVisitedNode = VisitedNode(0, -1)

        reverseInOrderTraverse(tree, k, latestVisitedNode)

        return latestVisitedNode.value
    }

    private fun reverseInOrderTraverse(node: BST?, k: Int, latest: VisitedNode) {
        if (node == null || latest.count >= k) return

        reverseInOrderTraverse(node.right, k, latest)
        if (latest.count < k) {
            latest.count++
            latest.value = node.value
            reverseInOrderTraverse(node.left, k, latest)
        }
    }

    private companion object {
        private const val EXPECTED_VALUE = 13
        private const val K = 4
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