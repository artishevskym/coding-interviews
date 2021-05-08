package com.artishevskym.codinginterviews.solutions.algoexpert.graphs

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write Node.depthFirstSearch method returning nodes names list in DFS order.
 *
 * @see <a href="https://www.algoexpert.io/questions/Depth-first%20Search">Depth First Search</a>
 */
class DepthFirstSearch {

    class Node(
        private val name: String,
        private val children: MutableList<Node> = mutableListOf()
    ) {

        // O(v+e) time | O(v) - space, v - vertices
        fun depthFirstSearch(): List<String> {
            return depthFirstSearch(mutableListOf())
        }

        private fun depthFirstSearch(result: MutableList<String>): List<String> {
            result.add(this.name)

            for (child in this.children) {
                child.depthFirstSearch(result)
            }

            return result
        }
    }

    @Test
    fun testSolution() {
        GRAPH.depthFirstSearch() shouldBeEqualTo OUTPUT
    }

    private companion object {
        private val GRAPH = Node(
            "A",
            mutableListOf(
                Node(
                    "B",
                    mutableListOf(
                        Node("E"),
                        Node("F", mutableListOf(Node("I"), Node("J")))
                    )
                ),
                Node("C"),
                Node(
                    "D",
                    mutableListOf(
                        Node("G", mutableListOf(Node("K"))),
                        Node("H")
                    )
                )
            )
        )
        private val OUTPUT = listOf("A", "B", "E", "F", "I", "J", "C", "D", "G", "K", "H")
    }
}