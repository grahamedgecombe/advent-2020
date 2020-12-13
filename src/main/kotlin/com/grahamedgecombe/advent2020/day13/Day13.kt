package com.grahamedgecombe.advent2020.day13

import com.grahamedgecombe.advent2020.Puzzle
import com.grahamedgecombe.advent2020.UnsolvableException

object Day13 : Puzzle<Day13.Timetable>(13) {
    class Timetable(val firstTimestamp: Long, val buses: List<Long?>)

    override fun parse(input: Sequence<String>): Timetable {
        val lines = input.toList()
        check(lines.size == 2)

        val firstTimestamp = lines[0].toLong()
        val ids = lines[1].split(",").map { id ->
            if (id != "x") {
                id.toLong()
            } else {
                null
            }
        }.toList()

        return Timetable(firstTimestamp, ids)
    }

    override fun solvePart1(input: Timetable): Long {
        for (time in input.firstTimestamp..Long.MAX_VALUE) {
            for (bus in input.buses) {
                if (bus != null && time % bus == 0L) {
                    return (time - input.firstTimestamp) * bus
                }
            }
        }

        throw UnsolvableException()
    }
}
