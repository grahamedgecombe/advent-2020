package com.grahamedgecombe.advent2020.day3

import com.grahamedgecombe.advent2020.day3.Day3
import kotlin.test.Test
import kotlin.test.assertEquals

object Day3Test {
    private val INPUT = Day3.parse(sequenceOf(
        "..##.......",
        "#...#...#..",
        ".#....#..#.",
        "..#.#...#.#",
        ".#...##..#.",
        "..#.##.....",
        ".#.#.#....#",
        ".#........#",
        "#.##...#...",
        "#...##....#",
        ".#..#...#.#"
    ))

    @Test
    fun testPart1() {
        assertEquals("7", Day3.solvePart1(INPUT))
        assertEquals("268", Day3.solvePart1(Day3.parse()))
    }
}
