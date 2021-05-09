package com.artishevskym.codinginterviews.solutions.algoexpert.bst

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Implement a BST operations: insert, remove, contains.
 *
 * @see <a href="https://www.algoexpert.io/questions/BST%20Construction">BST Construction</a>
 */
class BSTConstruction {

    @Test
    fun testRecursiveSolution() {
        val root = BSTRecursive(10)

        root.contains(10) shouldBeEqualTo true

        root.insert(5)
            .insert(15)
            .insert(2)
            .insert(5)
            .insert(13)
            .insert(22)
            .insert(1)
            .insert(14)
            .insert(12)
            .remove(10)

        root.contains(15) shouldBeEqualTo true
        root.contains(10) shouldBeEqualTo false
    }

    open class BSTRecursive(value: Int) {
        var value = value
        var left: BSTRecursive? = null
        var right: BSTRecursive? = null

        // Recursion
        // Avg: time: O(log(n)) | space: O(log(n))
        // Worst: time: O(n) | space: O(n)
        fun insert(value: Int): BSTRecursive {
            if (value < this.value) {
                if (this.left != null) {
                    this.left!!.insert(value)
                } else {
                    this.left = BSTRecursive(value)
                }
            } else {
                if (this.right != null) {
                    this.right!!.insert(value)
                } else {
                    this.right = BSTRecursive(value)
                }
            }
            return this
        }

        // Recursion
        // Avg: time: O(log(n)) | space: O(log(n))
        // Worst: time: O(n) | space: O(n)
        fun contains(value: Int): Boolean {
            if (value < this.value) {
                if (this.left != null) {
                    return this.left!!.contains(value)
                }
                return false
            } else if (value > this.value) {
                if (this.right != null) {
                    return this.right!!.contains(value)
                }
                return false
            } else {
                return true
            }
        }

        // Recursion
        // Avg: time: O(log(n)) | space: O(log(n))
        // Worst: time: O(n) | space: O(n)
        fun remove(value: Int, parent: BSTRecursive? = null): BSTRecursive {
            if (value < this.value) {
                // step 1. search node with value to remove
                if (this.left != null) {
                    this.left!!.remove(value, this)
                }
            } else if (value > this.value) {
                // step 1. search node with value to remove
                if (this.right != null) {
                    this.right!!.remove(value, this)
                }
            } else if (value == this.value) {
                // step 2. remove value
                if (this.left != null && this.right != null) {
                    // case with 2 children - replace node with min value from right subtree
                    this.value = this.right!!.getMinValue()
                    this.right!!.remove(this.value, this)
                } else if (parent?.left == this) {
                    // case with 1 child on the left side of parent
                    parent.left = if (this.left != null) this.left else this.right
                } else if (parent?.right == this) {
                    // case with 1 child on the right side of parent
                    parent.right = if (this.left != null) this.left else this.right
                } else if (parent == null) {
                    // removing root node with 1 or 0 children
                    if (this.left != null) {
                        this.value = this.left!!.value
                        this.right = this.left!!.right
                        this.left = this.left!!.left
                    } else if (this.right != null) {
                        this.value = this.right!!.value
                        this.left = this.right!!.left
                        this.right = this.right!!.right
                    } else {
                        // single-node tree, remove whole tree
                        // this.value = null
                    }
                }
            }

            return this
        }

        private fun getMinValue(): Int {
            if (this.left != null) {
                return this.left!!.getMinValue()
            } else {
                return this.value
            }
        }
    }

    @Test
    fun testIterativeSolution() {
        val root = BSTIterative(10)

        root.contains(10) shouldBeEqualTo true

        root.insert(5)
            .insert(15)
            .insert(2)
            .insert(5)
            .insert(13)
            .insert(22)
            .insert(1)
            .insert(14)
            .insert(12)
            .remove(10)

        root.contains(15) shouldBeEqualTo true
        root.contains(10) shouldBeEqualTo false
    }

    open class BSTIterative(value: Int) {
        var value = value
        var left: BSTIterative? = null
        var right: BSTIterative? = null

        // Iterative
        // Avg: time: O(log(n)) | space: O(1)
        // Worst: time: O(n) | space: O(1)
        fun insert(value: Int): BSTIterative {
            var current: BSTIterative? = this

            while (true) {
                if (value < current!!.value) {
                    if (current.left != null) {
                        current = current.left
                    } else {
                        current.left = BSTIterative(value)
                        break
                    }
                } else {
                    if (current.right != null) {
                        current = current.right
                    } else {
                        current.right = BSTIterative(value)
                        break
                    }
                }
            }

            return this
        }

        // Iterative
        // Avg: time: O(log(n)) | space: O(1)
        // Worst: time: O(n) | space: O(1)
        fun contains(value: Int): Boolean {
            var current: BSTIterative? = this

            while (current != null) {
                if (value < current.value) {
                    current = current.left
                } else if (value > current.value) {
                    current = current.right
                } else {
                    // value found
                    return true
                }
            }

            return false
        }

        // Iterative
        // Avg: time: O(log(n)) | space: O(1)
        // Worst: time: O(n) | space: O(1)
        fun remove(value: Int, parentNode: BSTIterative? = null): BSTIterative {
            var parent = parentNode
            var current: BSTIterative? = this

            while (current != null) {
                if (value < current.value) {
                    // step 1. search node with value to remove
                    parent = current
                    current = current.left
                } else if (value > current.value) {
                    // step 1. search node with value to remove
                    parent = current
                    current = current.right
                } else {
                    // step 2. remove value
                    if (current.left != null && current.right != null) {
                        // case with 2 children - replace node with min value from right subtree
                        current.value = current.right!!.getMinValue()
                        current.right!!.remove(current.value, current)
                    } else if (parent?.left == current) {
                        // case with 1 child on the left side of parent
                        parent.left = if (current.left != null) current.left else current.right
                    } else if (parent?.right == current) {
                        // case with 1 child on the right side of parent
                        parent.right = if (current.left != null) current.left else current.right
                    } else if (parent == null) {
                        // removing root node with 1 or 0 children
                        if (current.left != null) {
                            current.value = current.left!!.value
                            current.right = current.left!!.right
                            current.left = current.left!!.left
                        } else if (current.right != null) {
                            current.value = current.right!!.value
                            current.left = current.right!!.left
                            current.right = current.right!!.right
                        } else {
                            // single-node tree, remove whole tree
                            // current.value = null
                        }
                    }

                    // removing finished
                    break
                }
            }

            return this
        }

        private fun getMinValue(): Int {
            if (this.left != null) {
                return this.left!!.getMinValue()
            } else {
                return this.value
            }
        }
    }
}