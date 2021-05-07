package com.artishevskym.codinginterviews.solutions.algoexpert.binarytrees

import com.google.gson.Gson
import org.amshove.kluent.internal.assertEquals
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.math.exp

/**
 * Write a function inverting Binary Tree.
 *
 * @see <a href="https://www.algoexpert.io/questions/Invert%20Binary%20Tree">Invert Binary Tree</a>
 */
class InvertBinaryTree {
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
    private lateinit var outputRoot: BST

    @Before
    fun setup() {
        val json = Gson().fromJson(INPUT_JSON, BstJson::class.java)
        root = BST.create(json.root, json.nodes)

        val outputJson = Gson().fromJson(OUTPUT_JSON, BstJson::class.java)
        outputRoot = BST.create(outputJson.root, outputJson.nodes)
    }

    @Test
    fun testRecursive() {
        invertBinaryTreeRecursive(root)
        assert(isIdentical(root, outputRoot))
    }

    // time: O(n) | space: O(h)
    private fun invertBinaryTreeRecursive(tree: BST?) {
        if (tree == null) return
        swap(tree)
        invertBinaryTreeRecursive(tree.left)
        invertBinaryTreeRecursive(tree.right)
    }

    @Test
    fun testIterative() {
        invertBinaryTreeIterative(root)
        assert(isIdentical(root, outputRoot))
    }

    // time: O(n) | space: O(n)
    private fun invertBinaryTreeIterative(tree: BST) {
        val queue = ArrayDeque<BST>()
        queue.addLast(tree)

        while (queue.size > 0) {
            val node = queue.pollFirst()
            swap(node)
            if (node.left != null) queue.addLast(node.left)
            if (node.right != null) queue.addLast(node.right)
        }
    }

    private fun swap(node: BST) {
        val left = node.left
        node.left = node.right
        node.right = left
    }

    private fun isIdentical(root1: BST?, root2: BST?): Boolean {
        return if (root1 == null && root2 == null) {
            true
        } else if (root1 != null && root2 == null) {
            false
        } else if (root1 == null && root2 != null) {
            false
        } else { // check recursively
            root1?.value == root2?.value &&
                    isIdentical(root1?.left, root2?.left) &&
                    isIdentical(root1?.right, root2?.right)
        }
    }

    private companion object {
        private const val OUTPUT = 16
        private const val INPUT_JSON =
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
        private const val OUTPUT_JSON =
            """
{
    "root": "1",
    "nodes": [
        {"id": "1", "left": "3", "right": "2", "value": 1},
        {"id": "2", "left": "5", "right": "4", "value": 2},
        {"id": "3", "left": "7", "right": "6", "value": 3},
        {"id": "4", "left": "9", "right": "8", "value": 4},
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