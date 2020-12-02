package com.grahamedgecombe.advent2020.day2

import kotlin.test.Test
import kotlin.test.assertEquals

object Day2Test {
    private val INPUT = Day2.parse(sequenceOf(
        "1-3 a: abcde",
        "1-3 b: cdefg",
        "2-9 c: ccccccccc",
    ))

    @Test
    fun testPart1() {
        assertEquals("2", Day2.solvePart1(INPUT))
        assertEquals("458", Day2.solvePart1(Day2.parse()))
    }

    @Test
    fun testPart2() {
        assertEquals("1", Day2.solvePart2(INPUT))
        assertEquals("342", Day2.solvePart2(Day2.parse()))
    }
}
