package com.artishevskym.codinginterviews.solutions.algoexpert.binarytrees

import com.google.gson.Gson
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Write a function converting Binary Tree into the sum of its nodes' depths.
 *
 * @see <a href="https://www.algoexpert.io/questions/Node%20Depths">Node Depths</a>
 */
class NodeDepths {

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
    fun testRecursive() {
        nodeDepthsRecursive(root, 0) shouldBeEqualTo OUTPUT
    }

    // Average case: when the tree is balanced
    // O(n) time | O(h) space - where n is the number of nodes in
    // the Binary Tree and h is the height of the Binary Tree
    private fun nodeDepthsRecursive(root: BST?, depth: Int = 0): Int {
        if (root == null) return 0

        return depth +
                nodeDepthsRecursive(root.left, depth + 1) +
                nodeDepthsRecursive(root.right, depth + 1)
    }

    @Test
    fun testIterative() {
        nodeDepthsUsingStack(root) shouldBeEqualTo OUTPUT
    }

    open class Level(root: BST?, depth: Int) {
        val root = root
        val depth = depth
    }

    private fun nodeDepthsUsingStack(root: BST): Int {
        var sumOfDepths = 0
        val stack = Stack<Level>()

        stack.add(Level(root, 0))

        while (stack.size > 0) {
            val top = stack.pop()
            val node = top.root
            val depth = top.depth

            // if node is leaf, go to next node in the stack
            if (node == null) continue

            sumOfDepths += depth

            // add children to the stack
            stack.add(Level(node.left, depth + 1))
            stack.add(Level(node.right, depth + 1))
        }

        return sumOfDepths
    }

    private companion object {
        private const val OUTPUT = 16
        private const val BST_IN_JSON =
            """
{
    "root": "1",
    "nodes": [
        {"id": "1", "left": "2", "right": "3", "value": 1},
        {"id": "2", "left": "4", "right": "5", "value": 2},
        {"id": "3", "left": "6", "right": "7", "value": 3},
        {"id": "4", "left": "8", "right": "9", "value": 4},
        {"id": "5", "left": null, "right": null, "value": 5},
        {"id": "6", "left": null, "right": null, "value": 6},
        {"id": "7", "left": null, "right": null, "value": 7},
        {"id": "8", "left": null, "right": null, "value": 8},
        {"id": "9", "left": null, "right": null, "value": 9}
    ]
}
            """
    }
}