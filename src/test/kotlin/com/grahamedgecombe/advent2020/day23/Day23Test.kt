package com.grahamedgecombe.advent2020.day23

import kotlin.test.Test
import kotlin.test.assertEquals

object Day23Test {
    private val TEST_INPUT = Day23.parse(sequenceOf("389125467"))
    private val PROD_INPUT = Day23.parse()

    @Test
    fun testPart1() {
        assertEquals(67384529, Day23.solvePart1(TEST_INPUT))
        assertEquals(76385429, Day23.solvePart1(PROD_INPUT))
    }
}
