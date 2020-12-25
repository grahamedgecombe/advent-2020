package com.grahamedgecombe.advent2020.day15

import com.grahamedgecombe.advent2020.Puzzle

object Day15 : Puzzle<List<Int>>(15) {
    override fun parse(input: Sequence<String>): List<Int> {
        return input.first().split(",").map(String::toInt)
    }

    override fun solvePart1(input: List<Int>): Int {
        return solve(input, 2020)
    }

    override fun solvePart2(input: List<Int>): Int {
        return solve(input, 30000000)
    }

    private fun solve(input: List<Int>, turns: Int): Int {
        require(input.isNotEmpty())

        val history = IntArray(turns)
        var turn = 1
        var prevNumber = -1

        for (number in input) {
            history[number] = turn++
            prevNumber = number
        }

        while (true) {
            val prevTurn = history[prevNumber]

            val number = if (prevTurn != 0) {
                turn - 1 - prevTurn
            } else {
                0
            }

            if (turn == turns) {
                return number
            }

            history[prevNumber] = turn++ - 1
            prevNumber = number
        }
    }
}
