package com.grahamedgecombe.advent2020.day8

import kotlin.test.Test
import kotlin.test.assertEquals

object Day8Test {
    private val TEST_INPUT = Day8.parse(sequenceOf(
        "nop +0",
        "acc +1",
        "jmp +4",
        "acc +3",
        "jmp -3",
        "acc -99",
        "acc +1",
        "jmp -4",
        "acc +6"
    ))
    private val PROD_INPUT = Day8.parse()

    @Test
    fun testPart1() {
        assertEquals("5", Day8.solvePart1(TEST_INPUT))
        assertEquals("1137", Day8.solvePart1(PROD_INPUT))
    }
}
