package com.grahamedgecombe.advent2020.day7

import kotlin.test.Test
import kotlin.test.assertEquals

object Day7Test {
    private val TEST_INPUT = Day7.parse(sequenceOf(
        "light red bags contain 1 bright white bag, 2 muted yellow bags.",
        "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
        "bright white bags contain 1 shiny gold bag.",
        "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
        "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
        "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
        "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
        "faded blue bags contain no other bags.",
        "dotted black bags contain no other bags."
    ))
    private val PROD_INPUT = Day7.parse()

    @Test
    fun testPart1() {
        assertEquals("4", Day7.solvePart1(TEST_INPUT))
        assertEquals("226", Day7.solvePart1(PROD_INPUT))
    }
}
