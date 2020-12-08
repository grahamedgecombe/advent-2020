package com.grahamedgecombe.advent2020

import com.grahamedgecombe.advent2020.day1.Day1
import com.grahamedgecombe.advent2020.day2.Day2
import com.grahamedgecombe.advent2020.day3.Day3
import com.grahamedgecombe.advent2020.day4.Day4
import com.grahamedgecombe.advent2020.day5.Day5
import com.grahamedgecombe.advent2020.day6.Day6
import com.grahamedgecombe.advent2020.day7.Day7
import com.grahamedgecombe.advent2020.day8.Day8

fun main() {
    val puzzles = listOf<Puzzle<*>>(Day1, Day2, Day3, Day4, Day5, Day6, Day7, Day8)

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
