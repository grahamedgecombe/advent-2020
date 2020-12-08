package com.grahamedgecombe.advent2020.day7

import com.grahamedgecombe.advent2020.Puzzle

object Day7 : Puzzle<Day7.RuleSet>(7) {
    private const val SHINY_GOLD = "shiny gold"

    class RuleSet(rules: List<Rule>) {
        private val rules = rules.associateBy(Rule::color)
        private val rulesContaining = mutableMapOf<String, MutableSet<String>>()

        init {
            for (rule in rules) {
                for (child in rule.children.keys) {
                    val set = rulesContaining.getOrPut(child, ::mutableSetOf)
                    set += rule.color
                }
            }
        }

        fun countParents(color: String): Int {
            val parents = mutableSetOf<String>()
            findParents(color, parents)
            return parents.size
        }

        private fun findParents(color: String, parents: MutableSet<String>) {
            for (parent in getRulesContaining(color)) {
                parents += parent
                findParents(parent, parents)
            }
        }

        private fun getRulesContaining(color: String): Set<String> {
            return rulesContaining.getOrDefault(color, emptySet())
        }

        fun countChildren(color: String): Int {
            var count = 0

            val rule = rules[color]
            if (rule != null) {
                for ((childColor, quantity) in rule.children) {
                    count += (countChildren(childColor) + 1) * quantity
                }
            }

            return count
        }

        companion object {
            fun parse(input: Sequence<String>): RuleSet {
                return RuleSet(input.map(Rule::parse).toList())
            }
        }
    }

    data class Rule(val color: String, val children: Map<String, Int>) {
        companion object {
            private val RULE_REGEX = Regex("(.*) bags contain (.*)[.]")
            private val CHILD_REGEX = Regex("(\\d+) (.*?) bags?")

            fun parse(s: String): Rule {
                val match = RULE_REGEX.matchEntire(s) ?: throw IllegalArgumentException()

                val color = match.groupValues[1]
                val children = match.groupValues[2]

                val map = if (children == "no other bags") {
                    emptyMap()
                } else {
                    CHILD_REGEX.findAll(children).associate { childMatch ->
                        val quantity = childMatch.groupValues[1].toInt()
                        val childColor = childMatch.groupValues[2]
                        return@associate Pair(childColor, quantity)
                    }
                }

                return Rule(color, map)
            }
        }
    }

    override fun parse(input: Sequence<String>): RuleSet {
        return RuleSet.parse(input)
    }

    override fun solvePart1(input: RuleSet): String {
        return input.countParents(SHINY_GOLD).toString()
    }

    override fun solvePart2(input: RuleSet): String {
        return input.countChildren(SHINY_GOLD).toString()
    }
}
