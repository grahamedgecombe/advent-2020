package com.grahamedgecombe.advent2020.day5

import kotlin.test.Test
import kotlin.test.assertEquals

object Day5Test {
    private val PROD_INPUT = Day5.parse()

    @Test
    fun testPart1() {
        assertEquals(357, Day5.getSeat("FBFBBFFRLR"))
        assertEquals(567, Day5.getSeat("BFFFBBFRRR"))
        assertEquals(119, Day5.getSeat("FFFBBBFRRR"))
        assertEquals(820, Day5.getSeat("BBFFBBFRLL"))
        assertEquals("883", Day5.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals("532", Day5.solvePart2(PROD_INPUT))
    }
}
