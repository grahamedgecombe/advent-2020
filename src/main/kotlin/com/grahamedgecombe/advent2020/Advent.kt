package com.grahamedgecombe.advent2020

import com.grahamedgecombe.advent2020.day1.Day1
import com.grahamedgecombe.advent2020.day2.Day2

fun main() {
    val puzzles = listOf<Puzzle<*>>(Day1, Day2)

    for (puzzle in puzzles) {
        solve(puzzle)
    }
}

private fun <T> solve(puzzle: Puzzle<T>) {
    val input = puzzle.parse()

    val solutionPart1 = puzzle.solvePart1(input)
    if (solutionPart1 != null) {
        println("Day ${puzzle.number} Part 1: $solutionPart1")
    }

    val solutionPart2 = puzzle.solvePart2(input)
    if (solutionPart2 != null) {
        println("Day ${puzzle.number} Part 2: $solutionPart2")
    }
}
