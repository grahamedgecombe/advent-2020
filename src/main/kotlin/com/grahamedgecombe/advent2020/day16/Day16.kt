package com.grahamedgecombe.advent2020.day16

import com.grahamedgecombe.advent2020.Puzzle
import com.grahamedgecombe.advent2020.UnsolvableException

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

        private fun isTicketValid(ticket: Ticket): Boolean {
            for (value in ticket.values) {
                if (!isValueValid(value)) {
                    return false
                }
            }

            return true
        }

        fun getDepartureProduct(): Long {
            var product = 1L
            for ((i, field) in getFieldOrder().withIndex()) {
                if (field.name.startsWith("departure ")) {
                    product *= ticket.values[i]
                }
            }
            return product
        }

        private data class Key(val field: Field, val index: Int)

        private fun isFieldValidForIndex(field: Field, tickets: List<Ticket>, index: Int, cache: MutableMap<Key, Boolean>): Boolean {
            val key = Key(field, index)

            val valid = cache[key]
            if (valid != null) {
                return valid
            }

            for (ticket in tickets) {
                if (!field.validate(ticket.values[index])) {
                    cache[key] = false
                    return false
                }
            }

            cache[key] = true
            return true
        }

        fun getFieldOrder(): List<Field> {
            return getFieldOrder(emptyList(), fields, nearbyTickets.filter(::isTicketValid), mutableMapOf())
                ?: throw UnsolvableException()
        }

        private fun getFieldOrder(
            assignedFields: List<Field>,
            unassignedFields: List<Field>,
            tickets: List<Ticket>,
            cache: MutableMap<Key, Boolean>
        ): List<Field>? {
            if (unassignedFields.isEmpty()) {
                return assignedFields
            }

            val index = assignedFields.size

            for (field in unassignedFields) {
                if (isFieldValidForIndex(field, tickets, index, cache)) {
                    val solution = getFieldOrder(assignedFields.plus(field), unassignedFields.minus(field), tickets, cache)
                    if (solution != null) {
                        return solution
                    }
                }
            }

            return null
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

    override fun solvePart2(input: Input): Long {
        return input.getDepartureProduct()
    }
}
