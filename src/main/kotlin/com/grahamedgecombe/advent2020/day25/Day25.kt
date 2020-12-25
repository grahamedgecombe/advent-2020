package com.grahamedgecombe.advent2020.day25

import com.grahamedgecombe.advent2020.Puzzle
import com.grahamedgecombe.advent2020.UnsolvableException

object Day25 : Puzzle<Pair<Int, Int>>(25) {
    override fun parse(input: Sequence<String>): Pair<Int, Int> {
        val it = input.iterator()

        val first = it.next().toInt()
        val second = it.next().toInt()

        require(!it.hasNext())

        return Pair(first, second)
    }

    override fun solvePart1(input: Pair<Int, Int>): Int {
        var value = 1

        for (loopSize in 0..Int.MAX_VALUE) {
            if (value == input.first) {
                return transform(input.second, loopSize)
            } else if (value == input.second) {
                return transform(input.first, loopSize)
            }

            value = (value * 7) % 20201227
            if (value < 0) {
                value += 20201227
            }
        }

        throw UnsolvableException()
    }

    private fun transform(subjectNumber: Int, loopSize: Int): Int {
        var value = 1L
        for (i in 0 until loopSize) {
            value = (value * subjectNumber) % 20201227
            if (value < 0) {
                value += 20201227
            }
        }
        return value.toInt()
    }
}
