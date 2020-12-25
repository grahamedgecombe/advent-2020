package com.grahamedgecombe.advent2020.day19

import com.grahamedgecombe.advent2020.Puzzle
import com.grahamedgecombe.advent2020.UnsolvableException

object Day19 : Puzzle<Day19.Input>(19) {
    sealed class Rule {
        class Character(val char: Char) : Rule()
        class Alternation(val sequences: List<List<Int>>) : Rule()

        companion object {
            fun parse(s: String): Pair<Int, Rule> {
                val parts = s.split(':', limit = 2)
                require(parts.size == 2)

                val id = parts[0].toInt()
                val rule = parts[1].trim()

                if (rule.length == 3 && rule[0] == '"' && rule[2] == '"') {
                    return Pair(id, Character(rule[1]))
                }

                val sequences = rule.split('|').map {
                    it.split(' ').filter(String::isNotEmpty).map(String::toInt)
                }
                return Pair(id, Alternation(sequences))
            }
        }
    }

    class Input(private val rules: Map<Int, Rule>, private val messages: List<String>) {
        fun countMatchesPart1(): Int {
            return messages.count(::matchesPart1)
        }

        fun countMatchesPart2(): Int {
            return messages.count(::matchesPart2)
        }

        private fun matchesPart1(s: String): Boolean {
            return matches(s, 0, 0) == s.length
        }

        private fun matchesPart2(s: String): Boolean {
            // 0: 8 11
            // 8: 42 | 42 8
            // 11: 42 31 | 42 11 31
            //
            // 42+ 42{n} 31{n} where n >= 1

            // count the number of times 42 matches
            var index = 0
            var matches42 = 0
            while (true) {
                val n = matches(s, index, 42)
                if (n == 0) {
                    break
                }
                index += n
                matches42++
            }

            // count the number of times 31 matches
            var matches31 = 0
            while (true) {
                val n = matches(s, index, 31)
                if (n == 0) {
                    break
                }
                index += n
                matches31++
            }

            // we must have matched 42 at least once per 31, seen at least one 31, and be at the end of the input
            return matches31 in 1 until matches42 && index == s.length
        }

        // returns 0 if no match, otherwise returns the length of the match
        private fun matches(s: String, index: Int, ruleId: Int): Int {
            if (index >= s.length) {
                return 0
            }

            val rule = rules[ruleId] ?: throw UnsolvableException()
            when (rule) {
                is Rule.Character -> {
                    return if (s[index] == rule.char) {
                        1
                    } else {
                        0
                    }
                }
                is Rule.Alternation -> {
                    for (sequence in rule.sequences) {
                        val len = sequenceMatches(s, index, sequence)
                        if (len != 0) {
                            return len
                        }
                    }
                    return 0
                }
            }
        }

        private fun sequenceMatches(s: String, index: Int, sequence: List<Int>): Int {
            var len = 0

            for (ruleId in sequence) {
                val n = matches(s, index + len, ruleId)
                if (n == 0) {
                    return 0
                }
                len += n
            }

            return len
        }
    }

    override fun parse(input: Sequence<String>): Input {
        val it = input.iterator()

        val rules = mutableMapOf<Int, Rule>()
        while (true) {
            require(it.hasNext())

            val s = it.next()
            if (s.isEmpty()) {
                break
            }

            rules += Rule.parse(s)
        }

        val messages = mutableListOf<String>()
        it.forEachRemaining { messages += it }

        return Input(rules, messages)
    }

    override fun solvePart1(input: Input): Int {
        return input.countMatchesPart1()
    }

    override fun solvePart2(input: Input): Int {
        return input.countMatchesPart2()
    }
}
