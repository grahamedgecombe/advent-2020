package com.grahamedgecombe.advent2020.day9

import com.grahamedgecombe.advent2020.Puzzle

object Day9 : Puzzle<List<Long>>(9) {
    private const val PREAMBLE = 25

    override fun parse(input: Sequence<String>): List<Long> {
        return input.map(String::toLong).toList()
    }

    override fun solvePart1(input: List<Long>): String? {
        return getFirstInvalidNumber(input, PREAMBLE)?.toString()
    }

    fun getFirstInvalidNumber(numbers: List<Long>, preamble: Int): Long? {
        check(numbers.size >= preamble)

        for (i in preamble until numbers.size) {
            val n = numbers[i]

            var valid = false
            for (j in i - preamble until i) {
                for (k in i - preamble until j) {
                    if (numbers[j] + numbers[k] == n) {
                        valid = true
                        break
                    }
                }
            }

            if (!valid) {
                return n
            }
        }

        return null
    }
}
