package com.grahamedgecombe.advent2020.day11

import kotlin.test.Test
import kotlin.test.assertEquals

object Day11Test {
    private val TEST_INPUT = Day11.parse(sequenceOf(
        "L.LL.LL.LL",
        "LLLLLLL.LL",
        "L.L.L..L..",
        "LLLL.LL.LL",
        "L.LL.LL.LL",
        "L.LLLLL.LL",
        "..L.L.....",
        "LLLLLLLLLL",
        "L.LLLLLL.L",
        "L.LLLLL.LL"
    ))
    private val PROD_INPUT = Day11.parse()

    @Test
    fun testPart1() {
        assertEquals(37, Day11.solvePart1(TEST_INPUT))
        assertEquals(2093, Day11.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(26, Day11.solvePart2(TEST_INPUT))
        assertEquals(1862, Day11.solvePart2(PROD_INPUT))
    }
}
