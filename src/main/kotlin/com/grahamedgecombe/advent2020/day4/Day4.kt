package com.grahamedgecombe.advent2020.day4

import com.grahamedgecombe.advent2020.Puzzle

object Day4 : Puzzle<List<Day4.Passport>>(4) {
    class Passport(private val fields: Map<String, String>) {
        fun isValidPart1(): Boolean {
            return fields.keys.containsAll(REQUIRED_FIELDS)
        }

        companion object {
            private val REQUIRED_FIELDS = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
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
                fields[parts[0]] = parts[1]
            }
        }

        if (fields.isNotEmpty()) {
            passports += Passport(fields.toMap())
        }

        return passports
    }

    override fun solvePart1(input: List<Passport>): String {
        return input.count(Passport::isValidPart1).toString()
    }
}
