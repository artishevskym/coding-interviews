package com.artishevskym.codinginterviews.solutions.algoexpert.arrays

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Find a tournament winner. Given an array of pairs representing the teams that
 * have competed against each other and an array containing the results of each
 * competition.
 *  - competitions array has elements in the form of [homeTeam, awayTeam]
 *  - results array contains information about the winner [1 - home, 0 - away team won]
 * @see <a href="https://www.algoexpert.io/questions/Tournament%20Winner">Tournament Winner</a>
 */
class TournamentWinner {

    @Test
    fun testScoresTable() {
        tournamentWinner(COMPETITIONS, RESULTS) shouldBeEqualTo EXPECTED_WINNER
    }

    // time: O(n) | space: O(k)
    // n - number of competitions, k - number of teams
    private fun tournamentWinner(competitions: List<List<String>>, results: List<Int>): String {

        var bestTeam = ""
        var scores = mutableMapOf(bestTeam to 0)

        for (i in competitions.indices) {
            val (homeTeam, awayTeam) = competitions[i]
            val winner = if (results[i] == HOME_TEAM_WON) homeTeam else awayTeam
            updateScores(winner, scores)

            if (scores[winner]!! > scores[bestTeam]!!) {
                bestTeam = winner
            }
        }

        return bestTeam
    }

    private fun updateScores(team: String, scores: MutableMap<String, Int>) {
        if (team !in scores) {
            scores[team] = 0 // initiate score for a team
        }

        scores[team] = scores[team]!! + WINNER_POINTS
    }

    private companion object {
        private const val WINNER_POINTS = 3
        private const val HOME_TEAM_WON = 1

        private val COMPETITIONS = listOf(
            listOf("HTML", "C#"),
            listOf("C#", "Python"),
            listOf("Python", "HTML")
        )
        private val RESULTS = listOf(0, 0, 1)
        private const val EXPECTED_WINNER = "Python"
    }
}