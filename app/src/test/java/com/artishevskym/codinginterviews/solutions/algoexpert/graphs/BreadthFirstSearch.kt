package com.artishevskym.codinginterviews.solutions.algoexpert.graphs

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import java.util.*

/**
 * Implement BFS
 *
 * @see <a href="https://www.algoexpert.io/questions/Breadth-first%20Search">Breadth First Search</a>
 */
class BreadthFirstSearch {

    @Test
    fun testSolution() {
        GRAPH.breadthFirstSearch() shouldBeEqualTo OUTPUT
    }

    class Node(
        private val name: String,
        private val children: MutableList<Node> = mutableListOf()
    ) {
        // time: O(v+e) | space: O(v)
        fun breadthFirstSearch(): List<String> {
            val array = mutableListOf<String>()

            val queue = LinkedList<Node>()
            queue.add(this)
            while (queue.size != 0) {
                val node = queue.poll()
                array.add(node.name)
                queue.addAll(node.children)
            }

            return array
        }
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
        private val OUTPUT = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K")
    }
}