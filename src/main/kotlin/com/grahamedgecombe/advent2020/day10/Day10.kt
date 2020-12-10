package com.grahamedgecombe.advent2020.day10

import com.grahamedgecombe.advent2020.Puzzle
import com.grahamedgecombe.advent2020.UnsolvableException

object Day10 : Puzzle<Set<Int>>(10) {
    override fun parse(input: Sequence<String>): Set<Int> {
        return input.map(String::toInt).toSet()
    }

    override fun solvePart1(input: Set<Int>): String {
        return countDifferences(input).toString()
    }

    override fun solvePart2(input: Set<Int>): String {
        return countCombinations(input).toString()
    }

    fun countDifferences(bag: Set<Int>): Int {
        val max = bag.maxOrNull() ?: throw UnsolvableException()
        val chain = bag.plus(0).plus(max + 3).sorted()

        var ones = 0
        var threes = 0
        for (i in 0 until chain.size - 1) {
            val diff = chain[i + 1] - chain[i]
            when {
                diff == 1 -> ones++
                diff == 3 -> threes++
                diff != 2 -> throw UnsolvableException()
            }
        }
        return ones * threes
    }

    fun countCombinations(bag: Set<Int>): Long {
        val max = bag.maxOrNull() ?: throw UnsolvableException()
        return countCombinations(max + 3, bag, mutableMapOf())
    }

    private data class State(val target: Int, val bag: Set<Int>)

    private fun countCombinations(target: Int, bag: Set<Int>, cache: MutableMap<State, Long>): Long {
        val state = State(target, bag)

        val cachedCount = cache[state]
        if (cachedCount != null) {
            return cachedCount
        }

        var count = 0L

        for (diff in 1..3) {
            if (diff == target) {
                return 1
            } else if (!bag.contains(diff)) {
                continue
            } else {
                val newBag = bag.minus(diff)
                    .map { it - diff }
                    .filter { it >= 1 }
                    .toSet()
                count += countCombinations(target - diff, newBag, cache)
            }
        }

        cache[state] = count
        return count
    }
}
