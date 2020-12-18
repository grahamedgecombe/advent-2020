package com.grahamedgecombe.advent2020.day18

import kotlin.test.Test
import kotlin.test.assertEquals

object Day18Test {
    private const val TEST_INPUT_1 = "1 + 2 * 3 + 4 * 5 + 6"
    private const val TEST_INPUT_2 = "1 + (2 * 3) + (4 * (5 + 6))"
    private const val TEST_INPUT_3 = "2 * 3 + (4 * 5)"
    private const val TEST_INPUT_4 = "5 + (8 * 3 + 9 + 3 * 4 * 3)"
    private const val TEST_INPUT_5 = "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"
    private const val TEST_INPUT_6 = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
    private val PROD_INPUT = Day18.parse()

    @Test
    fun testPart1() {
        assertEquals(71, Day18.evaluatePart1(TEST_INPUT_1))
        assertEquals(51, Day18.evaluatePart1(TEST_INPUT_2))
        assertEquals(26, Day18.evaluatePart1(TEST_INPUT_3))
        assertEquals(437, Day18.evaluatePart1(TEST_INPUT_4))
        assertEquals(12240, Day18.evaluatePart1(TEST_INPUT_5))
        assertEquals(13632, Day18.evaluatePart1(TEST_INPUT_6))
        assertEquals(5374004645253, Day18.solvePart1(PROD_INPUT))
    }
}
