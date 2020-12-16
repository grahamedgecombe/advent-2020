package com.grahamedgecombe.advent2020.day16

import com.grahamedgecombe.advent2020.Puzzle

object Day16 : Puzzle<Day16.Input>(16) {
    data class Field(val name: String, val range1: IntRange, val range2: IntRange) {
        fun validate(value: Int): Boolean {
            return value in range1 || value in range2
        }

        companion object {
            private val REGEX = Regex("([^:]+): ([0-9]+)-([0-9]+) or ([0-9]+)-([0-9]+)")

            fun parse(s: String): Field {
                val result = REGEX.matchEntire(s) ?: throw IllegalArgumentException()

                val name = result.groupValues[1]
                val range1 = result.groupValues[2].toInt()..result.groupValues[3].toInt()
                val range2 = result.groupValues[4].toInt()..result.groupValues[5].toInt()

                return Field(name, range1, range2)
            }
        }
    }

    data class Ticket(val values: List<Int>) {
        companion object {
            fun parse(s: String): Ticket {
                return Ticket(s.split(",").map(String::toInt).toList())
            }
        }
    }

    class Input(private val fields: List<Field>, private val ticket: Ticket, private val nearbyTickets: List<Ticket>) {
        private fun isValueValid(value: Int): Boolean {
            for (field in fields) {
                if (field.validate(value)) {
                    return true
                }
            }

            return false
        }

        fun getErrorRate(): Int {
            var sum = 0

            for (ticket in this.nearbyTickets) {
                for (value in ticket.values) {
                    if (!isValueValid(value)) {
                        sum += value
                    }
                }
            }

            return sum
        }

        companion object {
            fun parse(input: Sequence<String>): Input {
                val it = input.iterator()

                val fields = mutableListOf<Field>()
                while (it.hasNext()) {
                    val s = it.next()
                    if (s == "") {
                        break
                    }
                    fields += Field.parse(s)
                }

                check(it.hasNext() && it.next() == "your ticket:")
                check(it.hasNext())

                val ticket = Ticket.parse(it.next())

                check(it.hasNext() && it.next() == "")
                check(it.hasNext() && it.next() == "nearby tickets:")

                val nearbyTickets = mutableListOf<Ticket>()
                while (it.hasNext()) {
                    nearbyTickets += Ticket.parse(it.next())
                }

                return Input(fields, ticket, nearbyTickets)
            }
        }
    }

    override fun parse(input: Sequence<String>): Input {
        return Input.parse(input)
    }

    override fun solvePart1(input: Input): Int {
        return input.getErrorRate()
    }
}
