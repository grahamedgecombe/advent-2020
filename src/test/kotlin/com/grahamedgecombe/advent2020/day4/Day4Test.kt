package com.grahamedgecombe.advent2020.day4

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

object Day4Test {
    private val PROD_INPUT = Day4.parse()

    @Test
    fun testPart1() {
        assertEquals("2", Day4.solvePart1(Day4.parse(sequenceOf(
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
            "byr:1937 iyr:2017 cid:147 hgt:183cm",
            "",
            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
            "hcl:#cfa07d byr:1929",
            "",
            "hcl:#ae17e1 iyr:2013",
            "eyr:2024",
            "ecl:brn pid:760753108 byr:1931",
            "hgt:179cm",
            "",
            "hcl:#cfa07d eyr:2025 pid:166559648",
            "iyr:2011 ecl:brn hgt:59in"
        ))))
        assertEquals("170", Day4.solvePart1(PROD_INPUT))
    }
}
