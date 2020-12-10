package com.grahamedgecombe.advent2020.day10

import kotlin.test.Test
import kotlin.test.assertEquals

object Day10Test {
    private val TEST_INPUT_1 = setOf(
        16,
        10,
        15,
        5,
        1,
        11,
        7,
        19,
        6,
        12,
        4
    )
    private val TEST_INPUT_2 = setOf(
        28,
        33,
        18,
        42,
        31,
        14,
        46,
        20,
        48,
        47,
        24,
        23,
        49,
        45,
        19,
        38,
        39,
        11,
        1,
        32,
        25,
        35,
        8,
        17,
        7,
        9,
        4,
        2,
        34,
        10,
        3
    )
    private val PROD_INPUT = Day10.parse()

    @Test
    fun testPart1() {
        assertEquals(35, Day10.countDifferences(TEST_INPUT_1))
        assertEquals(220, Day10.countDifferences(TEST_INPUT_2))
        assertEquals("2432", Day10.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(8, Day10.countCombinations(TEST_INPUT_1))
        assertEquals(19208, Day10.countCombinations(TEST_INPUT_2))
        assertEquals("453551299002368", Day10.solvePart2(PROD_INPUT))
    }
}
