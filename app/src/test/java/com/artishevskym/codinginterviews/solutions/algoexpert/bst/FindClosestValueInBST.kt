package com.artishevskym.codinginterviews.solutions.algoexpert.bst

import com.google.gson.Gson
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import kotlin.math.abs

/**
 * Find closest value in BST.
 * @see <a href="https://www.algoexpert.io/questions/Find%20Closest%20Value%20In%20BST">Find Closest Value In BST</a>
 */
class FindClosestValueInBST {

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
    fun testRecursiveTraversal() {
        findClosestValueInBstRecursive(root, TARGET) shouldBeEqualTo EXPECTED_RESULT
    }

    private fun findClosestValueInBstRecursive(tree: BST, target: Int): Int {
        return helperRecursive(tree, target, tree.value)
    }

    private fun helperRecursive(tree: BST, target: Int, closestValue: Int): Int {
        var currentClosestValue = closestValue

        if (abs(target - tree.value) < abs(target - closestValue)) {
            currentClosestValue = tree.value
        }

        // recursive traversal
        return if (target < tree.value && tree.left != null) {
            helperRecursive(tree.left!!, target, currentClosestValue)
        } else if (target > tree.value && tree.right != null) {
            helperRecursive(tree.right!!, target, currentClosestValue)
        } else {
            currentClosestValue
        }
    }

    @Test
    fun testIterativeTraversal() {

    }

    private companion object {
        private const val TARGET = 12
        private const val EXPECTED_RESULT = 13
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