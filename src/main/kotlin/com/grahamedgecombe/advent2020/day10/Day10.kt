package com.grahamedgecombe.advent2020.day10

import com.grahamedgecombe.advent2020.Puzzle

object Day10 : Puzzle<Set<Int>>(10) {
    override fun parse(input: Sequence<String>): Set<Int> {
        return input.map(String::toInt).toSet()
    }

    override fun solvePart1(input: Set<Int>): String {
        return countDifferences(input).toString()
    }

    fun countDifferences(bag: Set<Int>): Int? {
        val max = bag.maxOrNull() ?: return null
        val chain = bag.plus(0).plus(max + 3).sorted()

        var ones = 0
        var threes = 0
        for (i in 0 until chain.size - 1) {
            val diff = chain[i + 1] - chain[i]
            when {
                diff == 1 -> ones++
                diff == 3 -> threes++
                diff != 2 -> return null
            }
        }
        return ones * threes
    }
}
