package com.grahamedgecombe.advent2020.day9

import com.grahamedgecombe.advent2020.Puzzle
import com.grahamedgecombe.advent2020.UnsolvableException
import kotlin.math.max
import kotlin.math.min

object Day9 : Puzzle<List<Long>>(9) {
    private const val PREAMBLE = 25

    override fun parse(input: Sequence<String>): List<Long> {
        return input.map(String::toLong).toList()
    }

    override fun solvePart1(input: List<Long>): Long {
        return getFirstInvalidNumber(input, PREAMBLE) ?: throw UnsolvableException()
    }

    override fun solvePart2(input: List<Long>): Long {
        val invalidNumber = getFirstInvalidNumber(input, PREAMBLE) ?: throw UnsolvableException()
        return getWeakness(input, invalidNumber) ?: throw UnsolvableException()
    }

    fun getFirstInvalidNumber(numbers: List<Long>, preamble: Int): Long? {
        check(numbers.size >= preamble)

        for (i in preamble until numbers.size) {
            val n = numbers[i]

            var valid = false
            for (j in i - preamble until i) {
                for (k in i - preamble until j) {
                    if (numbers[j] + numbers[k] == n) {
                        valid = true
                        break
                    }
                }
            }

            if (!valid) {
                return n
            }
        }

        return null
    }

    fun getWeakness(numbers: List<Long>, invalidNumber: Long): Long? {
        for (i in numbers.indices) {
            var sum = numbers[i]
            var min = sum
            var max = sum

            for (j in i + 1 until numbers.size) {
                val n = numbers[j]

                sum += n
                min = min(min, n)
                max = max(max, n)

                if (sum == invalidNumber) {
                    return min + max
                } else if (sum > invalidNumber) {
                    break
                }
            }
        }

        return null
    }
}
