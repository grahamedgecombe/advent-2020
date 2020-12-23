package com.grahamedgecombe.advent2020.day23

import com.grahamedgecombe.advent2020.Puzzle
import com.grahamedgecombe.advent2020.UnsolvableException

object Day23 : Puzzle<List<Int>>(23) {
    override fun parse(input: Sequence<String>): List<Int> {
        return input.single().map { it - '0' }.toList()
    }

    private class Node(val value: Int) {
        var previous: Node? = null
        var next: Node? = null

        fun getSolution(): Int {
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
            for (j in current.value - 1 downTo min) {
                val node = nodes[j]
                if (node == null || node == cup1 || node == cup2 || node == cup3) {
                    continue
                }
                return node
            }

            for (j in max downTo current.value + 1) {
                val node = nodes[j]
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
                cup3.next!!.previous = current

                val destination = getDestination(current, cup1, cup2, cup3)

                destination.next!!.previous = cup3
                cup3.next = destination.next

                destination.next = cup1
                cup1.previous = destination

                current = current.next!!
            }
        }

        fun getSolution(): Int {
            val one = nodes[1] ?: throw UnsolvableException()
            return one.getSolution()
        }

        companion object {
            fun create(input: List<Int>): Game {
                val head = Node(input.first())

                val min = input.minOrNull()!!
                val max = input.maxOrNull()!!

                val nodes = arrayOfNulls<Node?>(max + 1)
                nodes[head.value] = head

                var previous = head

                for (value in input.slice(1 until input.size)) {
                    val current = Node(value)
                    nodes[value] = current

                    previous.next = current
                    current.previous = previous

                    previous = current
                }

                previous.next = head
                head.previous = previous

                return Game(head, nodes, min, max)
            }
        }
    }

    override fun solvePart1(input: List<Int>): Int {
        val game = Game.create(input)
        game.run(100)
        return game.getSolution()
    }
}
