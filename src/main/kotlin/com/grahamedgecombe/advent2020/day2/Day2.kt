package com.grahamedgecombe.advent2020.day2

import com.grahamedgecombe.advent2020.Puzzle

object Day2 : Puzzle<List<Day2.Entry>>(2) {
    data class Entry(val min: Int, val max: Int, val char: Char, val password: String) {
        fun isValidPart1(): Boolean {
            val count = password.count { it == char }
            return count in min..max
        }

        fun isValidPart2(): Boolean {
            val char1 = password[min - 1]
            val char2 = password[max - 1]
            return (char1 == char) xor (char2 == char)
        }

        companion object {
            private val REGEX = Regex("(\\d+)-(\\d+) (\\w): (\\w+)")

            fun parse(s: String): Entry {
                val result = REGEX.matchEntire(s) ?: throw IllegalArgumentException()

                val min = result.groupValues[1].toInt()
                val max = result.groupValues[2].toInt()
                val char = result.groupValues[3][0]
                val password = result.groupValues[4]

                return Entry(min, max, char, password)
            }
        }
    }

    override fun parse(input: Sequence<String>): List<Entry> {
        return input.map(Entry.Companion::parse).toList()
    }

    override fun solvePart1(input: List<Entry>): Int {
        return input.count(Entry::isValidPart1)
    }

    override fun solvePart2(input: List<Entry>): Int {
        return input.count(Entry::isValidPart2)
    }
}
