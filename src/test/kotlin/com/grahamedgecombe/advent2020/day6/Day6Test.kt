package com.grahamedgecombe.advent2020.day6

import kotlin.test.Test
import kotlin.test.assertEquals

object Day6Test {
    private val TEST_INPUT = Day6.parse(sequenceOf(
        "abc",
        "",
        "a",
        "b",
        "c",
        "",
        "ab",
        "ac",
        "",
        "a",
        "a",
        "a",
        "a",
        "",
        "b"
    ))
    private val PROD_INPUT = Day6.parse()

    @Test
    fun testPart1() {
        assertEquals("6", Day6.solvePart1(Day6.parse(sequenceOf(
            "abcx",
            "abcy",
            "abcz"
        ))))

        assertEquals("11", Day6.solvePart1(TEST_INPUT))
        assertEquals("6259", Day6.solvePart1(PROD_INPUT))
    }
}
