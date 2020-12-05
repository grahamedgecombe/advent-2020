package com.grahamedgecombe.advent2020.day5

import com.grahamedgecombe.advent2020.Puzzle

object Day5 : Puzzle<List<Int>>(5) {
    private const val ROWS = 128
    private const val COLUMNS = 8

    override fun parse(input: Sequence<String>): List<Int> {
        return input.map(::getSeat).toList()
    }

    override fun solvePart1(input: List<Int>): String? {
        return input.maxOrNull()?.toString()
    }

    override fun solvePart2(input: List<Int>): String? {
        val seats = input.toSet()

        for (seat in 1 until (ROWS * COLUMNS - 1)) { // exclude the first and last elements
            if (!seats.contains(seat) && seats.contains(seat - 1) && seats.contains(seat + 1)) {
                return seat.toString()
            }
        }

        return null
    }

    fun getSeat(pass: String): Int {
        val (rows, columns) = getSeat(pass, 0 until ROWS, 0 until COLUMNS)
        return rows.single() * COLUMNS + columns.single()
    }

    private fun getSeat(pass: String, rows: IntRange, columns: IntRange): Pair<IntRange, IntRange> {
        if (pass.isEmpty()) {
            return Pair(rows, columns)
        }

        val head = pass.first()
        val tail = pass.substring(1)

        return when (head) {
            'F' -> getSeat(tail, rows.lower(), columns)
            'B' -> getSeat(tail, rows.upper(), columns)
            'L' -> getSeat(tail, rows, columns.lower())
            'R' -> getSeat(tail, rows, columns.upper())
            else -> throw IllegalArgumentException()
        }
    }

    private fun IntRange.lower(): IntRange {
        val midpoint = (first + (last + 1)) / 2
        return first until midpoint
    }

    private fun IntRange.upper(): IntRange {
        val midpoint = (first + (last + 1)) / 2
        return midpoint until (last + 1)
    }
}
