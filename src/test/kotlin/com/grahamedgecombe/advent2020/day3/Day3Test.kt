package com.grahamedgecombe.advent2020.day3

import kotlin.test.Test
import kotlin.test.assertEquals

object Day3Test {
    private val TEST_INPUT = Day3.parse(sequenceOf(
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
    private val PROD_INPUT = Day3.parse()

    @Test
    fun testPart1() {
        assertEquals(7, Day3.solvePart1(TEST_INPUT))
        assertEquals(268, Day3.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(336, Day3.solvePart2(TEST_INPUT))
        assertEquals(3093068400, Day3.solvePart2(PROD_INPUT))
    }
}
