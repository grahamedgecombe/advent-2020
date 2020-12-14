package com.grahamedgecombe.advent2020.day14

import kotlin.test.Test
import kotlin.test.assertEquals

object Day14Test {
    private val PROD_INPUT = Day14.parse()

    @Test
    fun testPart1() {
        assertEquals(165, Day14.solvePart1(Day14.parse(sequenceOf(
            "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
            "mem[8] = 11",
            "mem[7] = 101",
            "mem[8] = 0"
        ))))
        assertEquals(14553106347726, Day14.solvePart1(PROD_INPUT))
    }
}
