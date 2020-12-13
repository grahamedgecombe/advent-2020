package com.grahamedgecombe.advent2020.day13

import kotlin.test.Test
import kotlin.test.assertEquals

object Day13Test {
    private val TEST_INPUT = Day13.parse(sequenceOf(
        "939",
        "7,13,x,x,59,x,31,19"
    ))
    private val PROD_INPUT = Day13.parse()

    @Test
    fun testPart1() {
        assertEquals(295, Day13.solvePart1(TEST_INPUT))
        assertEquals(171, Day13.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(1068781, Day13.solvePart2(TEST_INPUT))
        assertEquals(3417, Day13.chineseRemainderTheorem(listOf(17, null, 13, 19)))
        assertEquals(754018, Day13.chineseRemainderTheorem(listOf(67, 7, 59, 61)))
        assertEquals(779210, Day13.chineseRemainderTheorem(listOf(67, null, 7, 59, 61)))
        assertEquals(1261476, Day13.chineseRemainderTheorem(listOf(67, 7, null, 59, 61)))
        assertEquals(1202161486, Day13.chineseRemainderTheorem(listOf(1789, 37, 47, 1889)))
        assertEquals(539746751134958, Day13.solvePart2(PROD_INPUT))
    }
}
