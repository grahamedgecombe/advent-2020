package com.grahamedgecombe.advent2020.day22

import com.grahamedgecombe.advent2020.Puzzle

object Day22 : Puzzle<Day22.Input>(22) {
    data class Input(val player1: List<Int>, val player2: List<Int>)

    override fun parse(input: Sequence<String>): Input {
        val it = input.iterator()
        require(it.hasNext() && it.next() == "Player 1:")

        val player1 = mutableListOf<Int>()
        while (it.hasNext()) {
            val s = it.next()
            if (s.isEmpty()) {
                break
            }
            player1 += s.toInt()
        }

        require(it.hasNext() && it.next() == "Player 2:")

        val player2 = mutableListOf<Int>()
        while (it.hasNext()) {
            player2 += it.next().toInt()
        }

        return Input(player1, player2)
    }

    override fun solvePart1(input: Input): Int {
        val player1 = ArrayDeque(input.player1)
        val player2 = ArrayDeque(input.player2)

        while (player1.isNotEmpty() && player2.isNotEmpty()) {
            val head1 = player1.removeFirst()
            val head2 = player2.removeFirst()

            if (head1 > head2) {
                player1.addLast(head1)
                player1.addLast(head2)
            } else {
                player2.addLast(head2)
                player2.addLast(head1)
            }
        }

        return if (player1.isNotEmpty()) {
            getScore(player1)
        } else {
            getScore(player2)
        }
    }

    private fun getScore(deck: List<Int>): Int {
        var score = 0
        for ((i, card) in deck.withIndex()) {
            score += (deck.size - i) * card
        }
        return score
    }
}
