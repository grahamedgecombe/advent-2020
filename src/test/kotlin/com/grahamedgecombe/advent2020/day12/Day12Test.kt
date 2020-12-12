package com.grahamedgecombe.advent2020.day12

import kotlin.test.Test
import kotlin.test.assertEquals

object Day12Test {
    private val TEST_INPUT = Day12.parse(sequenceOf(
        "F10",
        "N3",
        "F7",
        "R90",
        "F11"
    ))
    private val PROD_INPUT = Day12.parse()

    @Test
    fun testPart1() {
        assertEquals("25", Day12.solvePart1(TEST_INPUT))
        assertEquals("759", Day12.solvePart1(PROD_INPUT))
    }
}
