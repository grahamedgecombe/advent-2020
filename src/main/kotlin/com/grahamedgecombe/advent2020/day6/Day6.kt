package com.grahamedgecombe.advent2020.day6

import com.grahamedgecombe.advent2020.Puzzle

object Day6 : Puzzle<List<Day6.Group>>(6) {
    class Group(private val people: List<Set<Char>>) {
        fun unionSize(): Int {
            return people.reduce { a, b -> a union b }.size
        }
    }

    override fun parse(input: Sequence<String>): List<Group> {
        val groups = mutableListOf<Group>()
        val people = mutableListOf<Set<Char>>()

        for (line in input) {
            if (line.isEmpty() && people.isNotEmpty()) {
                groups += Group(people.toList())
                people.clear()
                continue
            }

            people += line.asSequence().toSet()
        }

        if (people.isNotEmpty()) {
            groups += Group(people)
        }

        return groups
    }

    override fun solvePart1(input: List<Group>): String {
        return input.map(Group::unionSize).sum().toString()
    }
}
