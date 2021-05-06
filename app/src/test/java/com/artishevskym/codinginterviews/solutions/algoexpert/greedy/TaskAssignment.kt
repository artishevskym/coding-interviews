package com.artishevskym.codinginterviews.solutions.algoexpert.greedy

import org.amshove.kluent.shouldContainSame
import org.junit.Test

/**
 * Write a function that returns the optimal assignment of tasks to each worker such that the tasks
 * are completed as fast as possible.
 * - k - number of workers
 * - tasks - array representing durations of tasks
 * Return - list of pairs (indices of the tasks per one worker)
 * Note: number of tasks will always be equal to 2k => each worker always has exactly two tasks to
 * complete.
 * @see <a href="https://www.algoexpert.io/questions/Task%20Assignment">Task Assignment</a>
 */
@ExperimentalStdlibApi
class TaskAssignment {

    @Test
    fun testSolution(){
        taskAssignment(K, TASKS) shouldContainSame OUTPUT
    }

    // time: O(nlog(n)) | space: O(n)
    private fun taskAssignment(k: Int, tasks: List<Int>): List<List<Int>> {
        val taskIndexPairs = mutableListOf<List<Int>>()

        // build hash table as map <duration, indices>
        val taskDurationToIndicesMap = createTaskDurationToIndicesMap(tasks)
        val sortedTaskDurations = tasks.sorted() // shortest durations first

        // each worker greedily selects pair (min, max) of tasks durations
        for (i in 0 until k) {
            val task1Duration = sortedTaskDurations[i]
            val task1Index = taskDurationToIndicesMap[task1Duration]!!.removeLast()

            val task2Duration = sortedTaskDurations[tasks.size-1 - i]
            val task2Index = taskDurationToIndicesMap[task2Duration]!!.removeLast()

            taskIndexPairs.add(listOf(task1Index, task2Index))
        }

        return taskIndexPairs
    }

    private fun createTaskDurationToIndicesMap(tasks: List<Int>): MutableMap<Int, MutableList<Int>> {
        val map = mutableMapOf<Int, MutableList<Int>>()

        for (i in tasks.indices) {
            val duration = tasks[i]
            if (duration in map) {
                map[duration]!!.add(i)
            } else {
                map[duration] = mutableListOf(i)
            }
        }

        return map
    }

    private companion object {
        private const val K = 3
        private val TASKS = listOf(1, 3, 5, 3, 1, 4)
        private val OUTPUT = listOf(
            listOf(4, 2), // tasks[4] = 1; tasks[2] = 5; 1+5=6
            listOf(0, 5), // 5
            listOf(3, 1) // 6
        ) // the fastest time to complete all tasks by 3 workers is 6.
    }
}