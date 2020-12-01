package com.grahamedgecombe.advent2020.day1

import kotlin.test.Test
import kotlin.test.assertEquals

object Day1Test {
    private val INPUT = listOf(1721, 979, 366, 299, 675, 1456)

    @Test
    fun testPart1() {
        assertEquals("514579", Day1.solvePart1(INPUT))
        assertEquals("970816", Day1.solvePart1(Day1.parse()))
    }

    @Test
    fun testPart2() {
        assertEquals("241861950", Day1.solvePart2(INPUT))
        assertEquals("96047280", Day1.solvePart2(Day1.parse()))
    }
}
