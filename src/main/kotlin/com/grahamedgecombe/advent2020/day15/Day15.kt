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
        check(input.isNotEmpty())

        val history = mutableMapOf<Int, Pair<Int, Int?>>()
        var turn = 1
        var prevNumber = -1

        for (number in input) {
            history[number] = Pair(turn++, history[number]?.first)
            prevNumber = number
        }

        while (true) {
            val prevTurn = history[prevNumber]

            val first = prevTurn?.first
            val second = prevTurn?.second

            val number = if (first != null && second != null) {
                first - second
            } else {
                0
            }

            if (turn == turns) {
                return number
            }

            history[number] = Pair(turn++, history[number]?.first)
            prevNumber = number
        }
    }
}
