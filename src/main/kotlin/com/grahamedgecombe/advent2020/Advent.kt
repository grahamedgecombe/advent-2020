package com.grahamedgecombe.advent2020

import com.grahamedgecombe.advent2020.day1.Day1
import com.grahamedgecombe.advent2020.day10.Day10
import com.grahamedgecombe.advent2020.day11.Day11
import com.grahamedgecombe.advent2020.day12.Day12
import com.grahamedgecombe.advent2020.day13.Day13
import com.grahamedgecombe.advent2020.day14.Day14
import com.grahamedgecombe.advent2020.day15.Day15
import com.grahamedgecombe.advent2020.day16.Day16
import com.grahamedgecombe.advent2020.day17.Day17
import com.grahamedgecombe.advent2020.day2.Day2
import com.grahamedgecombe.advent2020.day3.Day3
import com.grahamedgecombe.advent2020.day4.Day4
import com.grahamedgecombe.advent2020.day5.Day5
import com.grahamedgecombe.advent2020.day6.Day6
import com.grahamedgecombe.advent2020.day7.Day7
import com.grahamedgecombe.advent2020.day8.Day8
import com.grahamedgecombe.advent2020.day9.Day9
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {
    val puzzles = listOf<Puzzle<*>>(
        Day1,
        Day2,
        Day3,
        Day4,
        Day5,
        Day6,
        Day7,
        Day8,
        Day9,
        Day10,
        Day11,
        Day12,
        Day13,
        Day14,
        Day15,
        Day16,
        Day17,
    )

    for (puzzle in puzzles) {
        solve(puzzle)
    }
}

@ExperimentalTime
private fun <T> solve(puzzle: Puzzle<T>) {
    val input = puzzle.parse()

    val solutionPart1 = measureTimedValue {
        puzzle.solvePart1(input)
    }
    println("Day ${puzzle.number} Part 1: ${solutionPart1.value} (${solutionPart1.duration})")

    val solutionPart2 = measureTimedValue {
        puzzle.solvePart2(input)
    }
    if (solutionPart2.value != null) {
        println("Day ${puzzle.number} Part 2: ${solutionPart2.value} (${solutionPart2.duration})")
    }
}
