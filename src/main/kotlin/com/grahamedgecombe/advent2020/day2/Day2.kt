package com.grahamedgecombe.advent2020.day2

import com.grahamedgecombe.advent2020.Puzzle

object Day2 : Puzzle<List<Day2.Entry>>(2) {
    data class Entry(val min: Int, val max: Int, val char: Char, val password: String) {
        fun isValidPart1(): Boolean {
            val count = password.filter { it == char }.count()
            return count in min..max
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

    override fun solvePart1(input: List<Entry>): String? {
        return input.filter(Entry::isValidPart1).count().toString()
    }
}
