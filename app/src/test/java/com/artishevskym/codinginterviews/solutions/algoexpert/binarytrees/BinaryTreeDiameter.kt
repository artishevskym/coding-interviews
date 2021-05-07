package com.artishevskym.codinginterviews.solutions.algoexpert.binarytrees

import com.google.gson.Gson
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import kotlin.math.max

/**
 * Write a function converting Binary Tree into its diameter.
 *
 * @see <a href="https://www.algoexpert.io/questions/Binary%20Tree%20Diameter">Binary Tree Diameter</a>
 */
class BinaryTreeDiameter {
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
        binaryTreeDiameter(root) shouldBeEqualTo OUTPUT
    }

    open class TreeInfo(diameter: Int, height: Int) {
        val diameter = diameter
        val height = height
    }

    // O(N) time | O(N) space
    private fun binaryTreeDiameter(tree: BST?): Int {
        return getTreeInfo(tree).diameter
    }

    private fun getTreeInfo(tree: BST?): TreeInfo {
        if (tree == null) return TreeInfo(0, 0)

        val leftTreeInfo = getTreeInfo(tree.left)
        val rightTreeInfo = getTreeInfo(tree.right)

        val longestPathThroughRoot = leftTreeInfo.height + rightTreeInfo.height
        val maxDiameterSoFar = max(leftTreeInfo.diameter, rightTreeInfo.diameter)
        val currentDiameter = max(longestPathThroughRoot, maxDiameterSoFar)
        val currentHeight = 1 + max(leftTreeInfo.height, rightTreeInfo.height)

        return TreeInfo(currentDiameter, currentHeight)
    }

    private companion object {
        private const val OUTPUT = 6 // 9 -> 8 -> 7 -> 3-> 4 -> 5 -> 6
        private const val BST_IN_JSON =
            """
{
    "root": "1",
    "nodes": [
        {"id": "1", "left": "3", "right": "2", "value": 1},
        {"id": "2", "left": null, "right": null, "value": 2},
        {"id": "3", "left": "7", "right": "4", "value": 3},
        {"id": "4", "left": null, "right": "5", "value": 4},
        {"id": "5", "left": null, "right": "6", "value": 5},
        {"id": "6", "left": null, "right": null, "value": 6},
        {"id": "7", "left": "8", "right": null, "value": 7},
        {"id": "8", "left": "9", "right": null, "value": 8},
        {"id": "9", "left": null, "right": null, "value": 9}
    ]
}
            """
    }
}