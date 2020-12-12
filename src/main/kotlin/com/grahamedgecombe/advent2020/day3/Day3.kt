package com.grahamedgecombe.advent2020.day3

import com.grahamedgecombe.advent2020.Puzzle
import java.util.BitSet

object Day3 : Puzzle<Day3.Grid>(3) {
    class Grid private constructor(
        private val width: Int,
        private val height: Int,
        private val trees: BitSet
    ) {
        fun countTrees(dx: Int, dy: Int): Long {
            var count = 0L
            var x = 0
            var y = 0

            while (y < height) {
                val tree = trees[y * width + (x % width)]
                if (tree) {
                    count++
                }

                x += dx
                y += dy
            }

            return count
        }

        companion object {
            fun parse(lines: List<String>): Grid {
                check(lines.isNotEmpty())

                val width = lines.first().length
                val height = lines.size

                val trees = BitSet(width * height)

                for ((y, line) in lines.withIndex()) {
                    for ((x, tile) in line.withIndex()) {
                        trees[y * width + x] = tile == '#'
                    }
                }

                return Grid(width, height, trees)
            }
        }
    }

    override fun parse(input: Sequence<String>): Grid {
        return Grid.parse(input.toList())
    }

    override fun solvePart1(input: Grid): Long {
        return input.countTrees(3, 1)
    }

    override fun solvePart2(input: Grid): Long {
        var result = input.countTrees(1, 1)
        result *= input.countTrees(3, 1)
        result *= input.countTrees(5, 1)
        result *= input.countTrees(7, 1)
        result *= input.countTrees(1, 2)
        return result
    }
}
