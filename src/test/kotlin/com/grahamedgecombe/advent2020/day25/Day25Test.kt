package com.grahamedgecombe.advent2020.day25

import kotlin.test.Test
import kotlin.test.assertEquals

object Day25Test {
    private val TEST_INPUT = Pair(5764801, 17807724)
    private val PROD_INPUT = Day25.parse()

    @Test
    fun testPart1() {
        assertEquals(14897079, Day25.solvePart1(TEST_INPUT))
        assertEquals(4968512, Day25.solvePart1(PROD_INPUT))
    }
}
