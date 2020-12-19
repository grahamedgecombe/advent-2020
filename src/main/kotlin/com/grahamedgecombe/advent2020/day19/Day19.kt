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
                check(parts.size == 2)

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
        fun countMatches(): Int {
            return messages.count(::matches)
        }

        private fun matches(s: String): Boolean {
            return matches(s, 0, rules[0] ?: throw UnsolvableException()) == s.length
        }

        // returns 0 if no match, otherwise returns the length of the match
        private fun matches(s: String, index: Int, rule: Rule): Int {
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
                val rule = rules[ruleId] ?: throw UnsolvableException()

                val n = matches(s, index + len, rule)
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
            check(it.hasNext())

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
        return input.countMatches()
    }
}
