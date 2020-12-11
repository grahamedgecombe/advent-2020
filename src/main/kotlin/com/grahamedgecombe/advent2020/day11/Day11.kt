package com.grahamedgecombe.advent2020.day11

import com.grahamedgecombe.advent2020.Puzzle

object Day11 : Puzzle<Day11.Grid>(11) {
    class Grid private constructor(
        private val chars: CharArray,
        private val width: Int,
        private val height: Int
    ) {
        private fun withinBounds(x: Int, y: Int): Boolean {
            return (x in 0 until width) && (y in 0 until height)
        }

        private fun get(x: Int, y: Int): Char {
            return if (withinBounds(x, y)) {
                chars[y * width + x]
            } else {
                FLOOR
            }
        }

        private fun countNeighboursPart1(x: Int, y: Int): Int {
            var count = 0

            for (dy in -1..1) {
                for (dx in -1..1) {
                    if (dx == 0 && dy == 0) {
                        continue
                    } else if (get(x + dx, y + dy) == OCCUPIED) {
                        count++
                    }
                }
            }

            return count
        }

        private fun countNeighboursPart2(x: Int, y: Int): Int {
            var count = 0

            for (dy in -1..1) {
                for (dx in -1..1) {
                    if (dx == 0 && dy == 0) {
                        continue
                    }

                    var rayX = x
                    var rayY = y
                    do {
                        rayX += dx
                        rayY += dy

                        if (!withinBounds(rayX, rayY)) {
                            break
                        }

                        val state = get(rayX, rayY)
                        if (state == OCCUPIED) {
                            count++
                        }
                    } while (state == FLOOR)
                }
            }

            return count
        }

        private fun next(countNeighbours: (Int, Int) -> Int, threshold: Int): Grid {
            val nextChars = CharArray(chars.size)
            var index = 0

            for (y in 0 until height) {
                for (x in 0 until width) {
                    val neighbours = countNeighbours(x, y)
                    val current = get(x, y)

                    val next = when {
                        current == EMPTY && neighbours == 0 -> OCCUPIED
                        current == OCCUPIED && neighbours >= threshold -> EMPTY
                        else -> current
                    }

                    nextChars[index++] = next
                }
            }

            return Grid(nextChars, width, height)
        }

        fun nextPart1(): Grid {
            return next(this::countNeighboursPart1, 4)
        }

        fun nextPart2(): Grid {
            return next(this::countNeighboursPart2, 5)
        }

        fun countOccupied(): Int {
            return chars.count { it == OCCUPIED }
        }

        override fun toString(): String {
            val builder = StringBuilder()
            var index = 0

            for (y in 0 until width) {
                for (x in 0 until height) {
                    builder.append(chars[index++])
                }
                builder.append('\n')
            }

            return builder.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Grid

            if (!chars.contentEquals(other.chars)) return false
            if (width != other.width) return false
            if (height != other.height) return false

            return true
        }

        override fun hashCode(): Int {
            var result = chars.contentHashCode()
            result = 31 * result + width
            result = 31 * result + height
            return result
        }

        companion object {
            private const val FLOOR = '.'
            private const val EMPTY = 'L'
            private const val OCCUPIED = '#'

            fun parse(input: List<String>): Grid {
                check(input.isNotEmpty())

                val height = input.size
                val width = input.first().length

                val chars = CharArray(width * height)

                for ((y, line) in input.withIndex()) {
                    check(line.length == width)

                    for ((x, char) in line.withIndex()) {
                        check(char == FLOOR || char == EMPTY || char == OCCUPIED)

                        val index = y * width + x
                        chars[index] = char
                    }
                }

                return Grid(chars, width, height)
            }
        }
    }

    override fun parse(input: Sequence<String>): Grid {
        return Grid.parse(input.toList())
    }

    override fun solvePart1(input: Grid): String {
        return countOccupiedAtFixedPoint(input) { it.nextPart1() }.toString()
    }

    override fun solvePart2(input: Grid): String {
        return countOccupiedAtFixedPoint(input) { it.nextPart2() }.toString()
    }

    private fun countOccupiedAtFixedPoint(input: Grid, next: (Grid) -> Grid): Int {
        var previous: Grid
        var current = input
        do {
            previous = current
            current = next(current)
        } while (current != previous)
        return current.countOccupied()
    }
}
