package com.artishevskym.codinginterviews.solutions.algoexpert.bst

import com.google.gson.Gson
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

/**
 * Write 3 functions that take in a BST and an empty array, traverse the BST, add nodes' values
 * to the input array and return that array.
 *
 * @see <a href="https://www.algoexpert.io/questions/BST%20Traversal">BST Traversal</a>
 */
class BSTTraversal {

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
    fun testInOrderTraverse() {
        inOrderTraverse(root, mutableListOf()) shouldBeEqualTo OUTPUT_IN_ORDER
    }

    // O(n) time | O(n) space
    private fun inOrderTraverse(tree: BST?, array: MutableList<Int>): List<Int> {
        if (tree != null) {
            inOrderTraverse(tree.left, array)
            array.add(tree.value)
            inOrderTraverse(tree.right, array)
        }
        return array
    }

    @Test
    fun testPreOrderTraverse() {
        preOrderTraverse(root, mutableListOf()) shouldBeEqualTo OUTPUT_PRE_ORDER
    }

    // O(n) time | O(n) space
    private fun preOrderTraverse(tree: BST?, array: MutableList<Int>): List<Int> {
        if (tree != null) {
            array.add(tree.value)
            preOrderTraverse(tree.left, array)
            preOrderTraverse(tree.right, array)
        }
        return array
    }

    @Test
    fun testPostOrderTraverse(){
        postOrderTraverse(root, mutableListOf()) shouldBeEqualTo OUTPUT_POST_ORDER
    }

    // O(n) time | O(n) space // recursive requires space
    private fun postOrderTraverse(tree: BST?, array: MutableList<Int>): List<Int> {
        if (tree != null) {
            postOrderTraverse(tree.left, array)
            postOrderTraverse(tree.right, array)
            array.add(tree.value)
        }
        return array
    }

    private companion object {
        private val OUTPUT_IN_ORDER = listOf(1,2,5,5,10,15,22)
        private val OUTPUT_PRE_ORDER = listOf(10,5,2,1,5,15,22)
        private val OUTPUT_POST_ORDER = listOf(1,2,5,5,22,15,10)
        private const val BST_IN_JSON =
            """
{
    "root": "10",
    "nodes": [
        {"id": "10",  "left": "5",  "right": "15",  "value": 10},
        {"id": "15",  "left": null, "right": "22",  "value": 15},
        {"id": "22",  "left": null, "right": null,  "value": 22},
        {"id": "5",   "left": "2",  "right": "5-2", "value": 5},
        {"id": "5-2", "left": null, "right": null,  "value": 5},
        {"id": "2",   "left": "1",  "right": null,  "value": 2},
        {"id": "1",   "left": null, "right": null,  "value": 1}
    ]
}
            """
    }
}