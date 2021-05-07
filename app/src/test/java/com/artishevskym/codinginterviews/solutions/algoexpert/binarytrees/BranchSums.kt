package com.artishevskym.codinginterviews.solutions.algoexpert.binarytrees

import com.artishevskym.codinginterviews.solutions.algoexpert.bst.BSTTraversal
import com.google.gson.Gson
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

/**
 * Write a function converting Binary Tree into a list of its branch sums ordered from
 * leftmost branch sum to rightmost branch sum.
 *
 * @see <a href="https://www.algoexpert.io/questions/Branch%20Sums">Branch Sums</a>
 */
class BranchSums {
    data class BstJson(
        val root: String,
        val nodes: List<BST.Companion.NodeJson>
    )

    open class BST(value: Int) {
        var value = value
        var left: BST? = null
        var right: BST? = null

        constructor(value: Int, left: BST?, right: BST?): this(value) {
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
    fun testSolution() {
        branchSums(root) shouldBeEqualTo OUTPUT
    }

    // O(n) time | O(n) space - where n is the number of nodes in the Binary Tree
    private fun branchSums(root: BST): List<Int> {
        val sums = mutableListOf<Int>()

        calculateBranchSums(root, 0, sums)

        return sums
    }

    private fun calculateBranchSums(node: BST?, runningSum: Int, sums: MutableList<Int>) {
        if (node == null) return

        val newRunningSum = runningSum + node.value
        if (node.left == null && node.right == null) {
            // leaf, add to sums and finish current branch traversing
            sums.add(newRunningSum)
            return
        }

        calculateBranchSums(node.left, newRunningSum, sums)
        calculateBranchSums(node.right, newRunningSum, sums)
    }

    private companion object {
        private val OUTPUT = listOf(15,16,18,10,11)
        // 15 = 1+2+4+8
        // 16 = 1+2+4+9
        // 18 = 1+2+5+10
        // 10 = 1+3+6
        // 11 = 1+3+7
        private const val BST_IN_JSON =
            """
{
    "root": "1",
    "nodes": [
        {"id": "1", "left": "2", "right": "3", "value": 1},
        {"id": "2", "left": "4", "right": "5", "value": 2},
        {"id": "3", "left": "6", "right": "7", "value": 3},
        {"id": "4", "left": "8", "right": "9", "value": 4},
        {"id": "5", "left": "10", "right": null, "value": 5},
        {"id": "6", "left": null, "right": null, "value": 6},
        {"id": "7", "left": null, "right": null, "value": 7},
        {"id": "8", "left": null, "right": null, "value": 8},
        {"id": "9", "left": null, "right": null, "value": 9},
        {"id": "10", "left": null, "right": null, "value": 10}
    ]
}
            """
    }
}