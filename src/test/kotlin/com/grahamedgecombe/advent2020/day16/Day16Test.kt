package com.grahamedgecombe.advent2020.day16

import kotlin.test.Test
import kotlin.test.assertEquals

object Day16Test {
    private val TEST_INPUT = Day16.parse(sequenceOf(
        "class: 1-3 or 5-7",
        "row: 6-11 or 33-44",
        "seat: 13-40 or 45-50",
        "",
        "your ticket:",
        "7,1,14",
        "",
        "nearby tickets:",
        "7,3,47",
        "40,4,50",
        "55,2,20",
        "38,6,12",
    ))
    private val PROD_INPUT = Day16.parse()

    @Test
    fun testPart1() {
        assertEquals(71, Day16.solvePart1(TEST_INPUT))
        assertEquals(20013, Day16.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(listOf("row", "class", "seat"), TEST_INPUT.getFieldOrder().map(Day16.Field::name))
        assertEquals(5977293343129, Day16.solvePart2(PROD_INPUT))
    }
}
