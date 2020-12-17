package com.grahamedgecombe.advent2020.day17

import kotlin.test.Test
import kotlin.test.assertEquals

object Day17Test {
    private val TEST_INPUT = Day17.parse(sequenceOf(
        ".#.",
        "..#",
        "###",
    ))
    private val PROD_INPUT = Day17.parse()

    @Test
    fun testPart1() {
        assertEquals(112, Day17.solvePart1(TEST_INPUT))
        assertEquals(348, Day17.solvePart1(PROD_INPUT))
    }
}
