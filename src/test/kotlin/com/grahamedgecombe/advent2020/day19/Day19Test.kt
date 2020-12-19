package com.grahamedgecombe.advent2020.day19

import kotlin.test.Test
import kotlin.test.assertEquals

object Day19Test {
    private val TEST_INPUT = Day19.parse(sequenceOf(
        "0: 4 1 5",
        "1: 2 3 | 3 2",
        "2: 4 4 | 5 5",
        "3: 4 5 | 5 4",
        "4: \"a\"",
        "5: \"b\"",
        "",
        "ababbb",
        "bababa",
        "abbbab",
        "aaabbb",
        "aaaabbb"
    ))
    private val PROD_INPUT = Day19.parse()

    @Test
    fun testPart1() {
        assertEquals(2, Day19.solvePart1(TEST_INPUT))
        assertEquals(173, Day19.solvePart1(PROD_INPUT))
    }
}
