package com.grahamedgecombe.advent2020.day9

import kotlin.test.Test
import kotlin.test.assertEquals

object Day9Test {
    private val TEST_INPUT = listOf<Long>(
        35,
        20,
        15,
        25,
        47,
        40,
        62,
        55,
        65,
        95,
        102,
        117,
        150,
        182,
        127,
        219,
        299,
        277,
        309,
        576
    )
    private val PROD_INPUT = Day9.parse()

    @Test
    fun testPart1() {
        assertEquals(127, Day9.getFirstInvalidNumber(TEST_INPUT, 5))
        assertEquals(144381670, Day9.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(62, Day9.getWeakness(TEST_INPUT, 127))
        assertEquals(20532569, Day9.solvePart2(PROD_INPUT))
    }
}
