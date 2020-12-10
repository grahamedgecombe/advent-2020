package com.grahamedgecombe.advent2020

import kotlin.streams.asSequence

abstract class Puzzle<T>(val number: Int) {
    fun parse(): T {
        return Puzzle::class.java.getResourceAsStream("day$number.txt").use { stream ->
            parse(stream.bufferedReader().lines().asSequence())
        }
    }

    abstract fun parse(input: Sequence<String>): T
    abstract fun solvePart1(input: T): String
    open fun solvePart2(input: T): String? = null
}
