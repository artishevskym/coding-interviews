package com.artishevskym.codinginterviews.solutions.algoexpert.graphs

import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function checking if graph represented as adjacency list contains a cycle.
 *
 * @see <a href="https://www.algoexpert.io/questions/Cycle%20In%20Graph">Cycle In Graph</a>
 */
class CycleInGraph {

    @Test
    fun testSolution() {
        cycleInGraph(EDGES) shouldBeEqualTo OUTPUT
    }

    // time: O(v+e) | space: O(v)
    private fun cycleInGraph(edges: List<List<Int>>): Boolean {
        val numberOfNodes = edges.size
        val visited = edges.map { false }.toMutableList()
        val currentlyInStack = edges.map { false }.toMutableList()

        for (node in 0 until numberOfNodes) {
            if (visited[node]) continue

            if (isNodeInCycle(node, edges, visited, currentlyInStack)) return true
        }

        return false
    }

    private fun isNodeInCycle(
        node: Int,
        edges: List<List<Int>>,
        visited: MutableList<Boolean>,
        currentlyInStack: MutableList<Boolean>
    ): Boolean {
        visited[node] = true
        currentlyInStack[node] = true

        val neighbors = edges[node]
        for (neighbor in neighbors) {
            if (!visited[neighbor]) {
                if (isNodeInCycle(neighbor, edges, visited, currentlyInStack)) return true
            } else if (currentlyInStack[neighbor]) return true
        }

        currentlyInStack[node] = false
        return false
    }

    @Test
    fun testSolutionWithColors() {
        cycleInGraphUsingColors(EDGES) shouldBeEqualTo OUTPUT
    }

    // time: O(v+e) | space: O(v)
    private fun cycleInGraphUsingColors(edges: List<List<Int>>): Boolean {
        val numberOfNodes = edges.size
        val colors = edges.map { _ -> WHITE }.toMutableList()

        for (node in 0 until numberOfNodes) {
            if (colors[node] != WHITE) continue

            if (isNodeInCycleUsingColors(node, edges, colors)) return true
        }

        return false
    }

    private fun isNodeInCycleUsingColors(
        node: Int,
        edges: List<List<Int>>,
        colors: MutableList<Int>
    ): Boolean {
        colors[node] = GREY

        val neighbors = edges[node]
        for (neighbor in neighbors) {
            val color = colors[neighbor]
            if (color == GREY) return true // cycle found
            if (color == BLACK) continue // node is already processed
            if (isNodeInCycleUsingColors(neighbor, edges, colors)) return true
        }

        colors[node] = BLACK
        return false
    }

    private companion object {
        private const val WHITE = 0 // unvisited node
        private const val GREY = 1 // visited node
        private const val BLACK = 2 // fully finished

        private val EDGES = listOf(
            listOf(1, 3),
            listOf(2, 3, 4),
            listOf(0),
            listOf(),
            listOf(2, 5),
            listOf()
        )

        private const val OUTPUT = true // i.e. 0->1->2->0; there are more
    }
}