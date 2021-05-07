package com.artishevskym.codinginterviews.solutions.algoexpert.linkedlists

import org.junit.Test

/**
 * Write a function removing duplicates from a singly linked list containing nodes in sorted order.
 *
 * @see <a href="https://www.algoexpert.io/questions/Remove%20Duplicates%20From%20Linked%20List">Remove Duplicates From Linked List</a>
 */
class RemoveDuplicatesFromLinkedList {

    // This is an input class. Do not edit.
    open class LinkedList(value: Int) {
        var value = value
        var next: LinkedList? = null
    }

    @Test
    fun testSolution() {
        val inputList = createLinkedList(INPUT)
        val outputList = createLinkedList(OUTPUT)

        assert(isIdentical(removeDuplicatesFromLinkedList(inputList), outputList))
    }

    private fun createLinkedList(input: List<Int>): LinkedList {
        val head = LinkedList(input[0])

        var current = head
        for (i in 1 until input.size) {
            val node = LinkedList(input[i])
            current.next = node
            current = node
        }

        return head
    }

    private fun isIdentical(list1: LinkedList?, list2: LinkedList?): Boolean {
        return if (list1 == null && list2 == null) {
            true
        } else if (list1 == null && list2 != null) {
            false
        } else if (list1 != null && list2 == null) {
            false
        } else {
            list1?.value == list2?.value && isIdentical(list1?.next, list2?.next)
        }
    }

    // O(n) time | O(1) space - where n is the number of nodes in the Linked List
    private fun removeDuplicatesFromLinkedList(linkedList: LinkedList): LinkedList {
        var currentNode: LinkedList? = linkedList

        while (currentNode != null) {
            var nextDistinctNode = currentNode.next

            // if duplicated value, go forward to distinct value
            while (nextDistinctNode != null && nextDistinctNode.value == currentNode.value) {
                nextDistinctNode = nextDistinctNode.next
            }

            // update next value
            currentNode.next = nextDistinctNode

            // move current node
            currentNode = nextDistinctNode
        }

        return linkedList
    }

    private companion object {
        private val INPUT = listOf(1, 1, 3, 4, 4, 4, 5, 6, 6)
        private val OUTPUT = listOf(1, 3, 4, 5, 6)
    }
}