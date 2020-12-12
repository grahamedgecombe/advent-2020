package com.grahamedgecombe.advent2020.day1

import com.grahamedgecombe.advent2020.Puzzle
import com.grahamedgecombe.advent2020.UnsolvableException

object Day1 : Puzzle<List<Int>>(1) {
    override fun parse(input: Sequence<String>): List<Int> {
        return input.map(String::toInt).toList()
    }

    override fun solvePart1(input: List<Int>): Int {
        for ((i, a) in input.withIndex()) {
            for (b in input.slice(0 until i)) {
                if (a + b == 2020) {
                    return a * b
                }
            }
        }

        throw UnsolvableException()
    }

    override fun solvePart2(input: List<Int>): Int {
        for ((i, a) in input.withIndex()) {
            for ((j, b) in input.slice(0 until i).withIndex()) {
                for (c in input.slice(0 until j)) {
                    if (a + b + c == 2020) {
                        return a * b * c
                    }
                }
            }
        }

        throw UnsolvableException()
    }
}
