package com.grahamedgecombe.advent2020.day13

import com.grahamedgecombe.advent2020.Puzzle
import com.grahamedgecombe.advent2020.UnsolvableException
import java.math.BigInteger

object Day13 : Puzzle<Day13.Timetable>(13) {
    class Timetable(val firstTimestamp: Long, val buses: List<Long?>)

    override fun parse(input: Sequence<String>): Timetable {
        val lines = input.toList()
        check(lines.size == 2)

        val firstTimestamp = lines[0].toLong()
        val ids = lines[1].split(",").map { id ->
            if (id != "x") {
                id.toLong()
            } else {
                null
            }
        }.toList()

        return Timetable(firstTimestamp, ids)
    }

    override fun solvePart1(input: Timetable): Long {
        for (time in input.firstTimestamp..Long.MAX_VALUE) {
            for (bus in input.buses) {
                if (bus != null && time % bus == 0L) {
                    return (time - input.firstTimestamp) * bus
                }
            }
        }

        throw UnsolvableException()
    }

    override fun solvePart2(input: Timetable): Long {
        return chineseRemainderTheorem(input.buses)
    }

    fun chineseRemainderTheorem(buses: List<Long?>): Long {
        // Constraints for 7,13,x,x,59,x,31,19 are:
        //
        // t     = 0 (mod 7)
        // t + 1 = 0 (mod 13)
        // t + 4 = 0 (mod 59)
        // t + 6 = 0 (mod 31)
        // t + 7 = 0 (mod 19)
        //
        // Can transform to a Chinese Remainder Theorem problem:
        //
        // x =  0 (mod 7)
        // x = -1 (mod 13)
        // x = -4 (mod 59)
        // x = -6 (mod 31)
        // x = -7 (mod 19)
        //
        // with m_i equal to the bus IDs and a_i equal to the positions in the
        // bus ID list, negated.
        //
        // Then x can be calculated using the equation listed under "For
        // Several Equations" at https://crypto.stanford.edu/pbc/notes/numbertheory/crt.html

        // Divisors must be pairwise coprime for the CRT to work
        checkPairwiseCoprime(buses.filterNotNull())

        val M = buses.filterNotNull().reduce(Long::times)

        var x = 0L
        for ((a, m) in buses.withIndex()) {
            if (m == null) {
                continue
            }

            val b = M / m
            val bPrime = b.modInverse(m)
            x -= a * b * bPrime
        }

        x %= M
        if (x < 0) {
            x += M
        }
        return x
    }

    private fun checkPairwiseCoprime(buses: List<Long>) {
        for ((i, v) in buses.withIndex()) {
            for (j in 0 until i) {
                if (gcd(v, buses[j]) != 1L) {
                    throw IllegalArgumentException()
                }
            }
        }
    }

    private fun gcd(a: Long, b: Long): Long {
        if (b == 0L) {
            return a
        }
        return gcd(b, a % b)
    }

    private fun Long.modInverse(m: Long): Long {
        return BigInteger.valueOf(this).modInverse(BigInteger.valueOf(m)).toLong()
    }
}
