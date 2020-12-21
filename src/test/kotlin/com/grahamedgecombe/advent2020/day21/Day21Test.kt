package com.grahamedgecombe.advent2020.day21

import kotlin.test.Test
import kotlin.test.assertEquals

object Day21Test {
    private val TEST_INPUT = Day21.parse(sequenceOf(
        "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
        "trh fvjkl sbzzf mxmxvkd (contains dairy)",
        "sqjhc fvjkl (contains soy)",
        "sqjhc mxmxvkd sbzzf (contains fish)",
    ))
    private val PROD_INPUT = Day21.parse()

    @Test
    fun testPart1() {
        assertEquals(5, Day21.solvePart1(TEST_INPUT))
        assertEquals(2307, Day21.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals("mxmxvkd,sqjhc,fvjkl", Day21.solvePart2(TEST_INPUT))
        assertEquals("cljf,frtfg,vvfjj,qmrps,hvnkk,qnvx,cpxmpc,qsjszn", Day21.solvePart2(PROD_INPUT))
    }
}
