package com.grahamedgecombe.advent2020.day1

import kotlin.test.Test
import kotlin.test.assertEquals

object Day1Test {
    private val TEST_INPUT = listOf(1721, 979, 366, 299, 675, 1456)
    private val PROD_INPUT = Day1.parse()

    @Test
    fun testPart1() {
        assertEquals("514579", Day1.solvePart1(TEST_INPUT))
        assertEquals("970816", Day1.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals("241861950", Day1.solvePart2(TEST_INPUT))
        assertEquals("96047280", Day1.solvePart2(PROD_INPUT))
    }
}
