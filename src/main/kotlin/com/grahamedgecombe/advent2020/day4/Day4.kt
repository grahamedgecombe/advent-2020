package com.grahamedgecombe.advent2020.day4

import com.grahamedgecombe.advent2020.Puzzle

object Day4 : Puzzle<List<Day4.Passport>>(4) {
    class Passport(private val fields: Map<String, String>) {
        fun isValidPart1(): Boolean {
            return fields.keys.containsAll(REQUIRED_FIELDS)
        }

        fun isValidPart2(): Boolean {
            if (!isValidPart1()) {
                return false
            }

            for ((key, value) in fields) {
                if (!isFieldValid(key, value)) {
                    return false
                }
            }

            return true
        }

        companion object {
            private val REQUIRED_FIELDS = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
            private val HEIGHT_REGEX = Regex("(\\d+)(cm|in)")
            private val COLOR_REGEX = Regex("#[\\da-f]{6}")
            private val EYE_COLORS = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
            private val ID_REGEX = Regex("\\d{9}")

            fun isFieldValid(key: String, value: String): Boolean {
                return when (key) {
                    "byr" -> isYearValid(value, 1920..2002)
                    "iyr" -> isYearValid(value, 2010..2020)
                    "eyr" -> isYearValid(value, 2020..2030)
                    "hgt" -> isHeightValid(value, cmRange = 150..193, inRange = 59..76)
                    "hcl" -> COLOR_REGEX.matches(value)
                    "ecl" -> value in EYE_COLORS
                    "pid" -> ID_REGEX.matches(value)
                    else -> true
                }
            }

            private fun isYearValid(value: String, range: IntRange): Boolean {
                val intValue = value.toIntOrNull() ?: return false
                return intValue in range
            }

            private fun isHeightValid(value: String, cmRange: IntRange, inRange: IntRange): Boolean {
                val match = HEIGHT_REGEX.matchEntire(value) ?: return false

                val intValue = match.groupValues[1].toIntOrNull() ?: return false
                val unit = match.groupValues[2]

                return when (unit) {
                    "cm" -> intValue in cmRange
                    "in" -> intValue in inRange
                    else -> false
                }
            }
        }
    }

    override fun parse(input: Sequence<String>): List<Passport> {
        val passports = mutableListOf<Passport>()
        val fields = mutableMapOf<String, String>()

        for (line in input) {
            if (line.isEmpty() && fields.isNotEmpty()) {
                passports += Passport(fields.toMap())
                fields.clear()
                continue
            }

            for (field in line.split(" ")) {
                val parts = field.split(":", limit = 2)
                require(parts.size == 2)
                fields[parts[0]] = parts[1]
            }
        }

        if (fields.isNotEmpty()) {
            passports += Passport(fields.toMap())
        }

        return passports
    }

    override fun solvePart1(input: List<Passport>): Int {
        return input.count(Passport::isValidPart1)
    }

    override fun solvePart2(input: List<Passport>): Int {
        return input.count(Passport::isValidPart2)
    }
}
