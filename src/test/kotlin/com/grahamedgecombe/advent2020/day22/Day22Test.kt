package com.grahamedgecombe.advent2020.day22

import kotlin.test.Test
import kotlin.test.assertEquals

object Day22Test {
    private val TEST_INPUT = Day22.parse(sequenceOf(
        "Player 1:",
        "9",
        "2",
        "6",
        "3",
        "1",
        "",
        "Player 2:",
        "5",
        "8",
        "4",
        "7",
        "10"
    ))
    private val PROD_INPUT = Day22.parse()

    @Test
    fun testPart1() {
        assertEquals(306, Day22.solvePart1(TEST_INPUT))
        assertEquals(32102, Day22.solvePart1(PROD_INPUT))
    }
}
