package com.grahamedgecombe.advent2020.day15

import kotlin.test.Test
import kotlin.test.assertEquals

object Day15Test {
    private val PROD_INPUT = Day15.parse()

    @Test
    fun testPart1() {
        assertEquals(436, Day15.solvePart1(listOf(0, 3, 6)))
        assertEquals(1, Day15.solvePart1(listOf(1, 3, 2)))
        assertEquals(10, Day15.solvePart1(listOf(2, 1, 3)))
        assertEquals(27, Day15.solvePart1(listOf(1, 2, 3)))
        assertEquals(78, Day15.solvePart1(listOf(2, 3, 1)))
        assertEquals(438, Day15.solvePart1(listOf(3, 2, 1)))
        assertEquals(1836, Day15.solvePart1(listOf(3, 1, 2)))
        assertEquals(203, Day15.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(175594, Day15.solvePart2(listOf(0, 3, 6)))
        assertEquals(2578, Day15.solvePart2(listOf(1, 3, 2)))
        assertEquals(3544142, Day15.solvePart2(listOf(2, 1, 3)))
        assertEquals(261214, Day15.solvePart2(listOf(1, 2, 3)))
        assertEquals(6895259, Day15.solvePart2(listOf(2, 3, 1)))
        assertEquals(18, Day15.solvePart2(listOf(3, 2, 1)))
        assertEquals(362, Day15.solvePart2(listOf(3, 1, 2)))
        assertEquals(9007186, Day15.solvePart2(PROD_INPUT))
    }
}
