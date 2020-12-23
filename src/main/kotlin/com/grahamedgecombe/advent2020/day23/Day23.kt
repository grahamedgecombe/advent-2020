package com.grahamedgecombe.advent2020.day23

import com.grahamedgecombe.advent2020.Puzzle
import com.grahamedgecombe.advent2020.UnsolvableException
import kotlin.math.max
import kotlin.math.min

object Day23 : Puzzle<List<Int>>(23) {
    override fun parse(input: Sequence<String>): List<Int> {
        return input.single().map { it - '0' }.toList()
    }

    private class Node(val value: Int) {
        var next: Node? = null

        fun getSolutionPart1(): Int {
            var solution = 0
            var node = next!!
            do {
                solution *= 10
                solution += node.value
                node = node.next!!
            } while (node != this)
            return solution
        }
    }

    private class Game private constructor(
        private val head: Node,
        private val nodes: Array<Node?>,
        private val min: Int,
        private val max: Int
    ) {
        private fun getDestination(current: Node, cup1: Node, cup2: Node, cup3: Node): Node {
            for (i in current.value - 1 downTo min) {
                val node = nodes[i]
                if (node == null || node == cup1 || node == cup2 || node == cup3) {
                    continue
                }
                return node
            }

            for (i in max downTo current.value + 1) {
                val node = nodes[i]
                if (node == null || node == cup1 || node == cup2 || node == cup3) {
                    continue
                }
                return node
            }

            throw UnsolvableException()
        }

        fun run(moves: Int) {
            var current = head

            for (i in 0 until moves) {
                val cup1 = current.next!!
                val cup2 = cup1.next!!
                val cup3 = cup2.next!!

                current.next = cup3.next

                val destination = getDestination(current, cup1, cup2, cup3)

                cup3.next = destination.next
                destination.next = cup1

                current = current.next!!
            }
        }

        fun getSolutionPart1(): Int {
            val one = nodes[1] ?: throw UnsolvableException()
            return one.getSolutionPart1()
        }

        fun getSolutionPart2(): Long {
            val one = nodes[1] ?: throw UnsolvableException()

            val a = one.next ?: throw UnsolvableException()
            val b = a.next ?: throw UnsolvableException()

            return a.value.toLong() * b.value.toLong()
        }

        companion object {
            fun createPart1(input: List<Int>): Game {
                require(input.isNotEmpty())
                return create(input.asSequence(), input.maxOrNull()!! + 1)
            }

            fun createPart2(input: List<Int>): Game {
                require(input.isNotEmpty())

                return create(sequence {
                    var max = Int.MIN_VALUE
                    var size = 0

                    for (value in input) {
                        yield(value)

                        max = max(max, value)
                        size++
                    }

                    for (i in 0 until (1000000 - size)) {
                        yield(max + i + 1)
                    }
                }, input.maxOrNull()!! + 1000001 - input.size)
            }

            private fun create(sequence: Sequence<Int>, size: Int): Game {
                val head = Node(sequence.first())

                val nodes = arrayOfNulls<Node?>(size)
                nodes[head.value] = head

                var min = head.value
                var max = head.value

                var previous = head

                for (value in sequence.drop(1)) {
                    val current = Node(value)
                    nodes[value] = current

                    previous.next = current

                    min = min(min, value)
                    max = max(max, value)

                    previous = current
                }

                previous.next = head

                return Game(head, nodes, min, max)
            }
        }
    }

    override fun solvePart1(input: List<Int>): Int {
        val game = Game.createPart1(input)
        game.run(100)
        return game.getSolutionPart1()
    }

    override fun solvePart2(input: List<Int>): Long {
        val game = Game.createPart2(input)
        game.run(10000000)
        return game.getSolutionPart2()
    }
}
