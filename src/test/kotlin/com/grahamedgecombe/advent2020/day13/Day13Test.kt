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
}
