package com.grahamedgecombe.advent2020.day2

import kotlin.test.Test
import kotlin.test.assertEquals

object Day2Test {
    private val TEST_INPUT = Day2.parse(sequenceOf(
        "1-3 a: abcde",
        "1-3 b: cdefg",
        "2-9 c: ccccccccc",
    ))
    private val PROD_INPUT = Day2.parse()

    @Test
    fun testPart1() {
        assertEquals("2", Day2.solvePart1(TEST_INPUT))
        assertEquals("458", Day2.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals("1", Day2.solvePart2(TEST_INPUT))
        assertEquals("342", Day2.solvePart2(PROD_INPUT))
    }
}
