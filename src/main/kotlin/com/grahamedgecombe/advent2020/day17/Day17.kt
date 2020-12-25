package com.grahamedgecombe.advent2020.day17

import com.grahamedgecombe.advent2020.Puzzle

object Day17 : Puzzle<Day17.Grid>(17) {
    data class Vector(val x: Int, val y: Int, val z: Int, val w: Int) {
        fun add(dx: Int, dy: Int, dz: Int, dw: Int): Vector {
            return Vector(x + dx, y + dy, z + dz, w + dw)
        }
    }

    data class Grid(
        val min: Vector,
        val max: Vector,
        val cubes: Set<Vector>
    ) {
        private fun countNeighbours(v: Vector): Int {
            var neighbours = 0

            for (dx in -1..1) {
                for (dy in -1..1) {
                    for (dz in -1..1) {
                        for (dw in -1..1) {
                            if (dx == 0 && dy == 0 && dz == 0 && dw == 0) {
                                continue
                            }

                            if (cubes.contains(v.add(dx, dy, dz, dw))) {
                                neighbours++
                            }
                        }
                    }
                }
            }

            return neighbours
        }

        private fun next(part2: Boolean): Grid {
            val nextCubes = mutableSetOf<Vector>()

            val dw = if (part2) 1 else 0
            val min = min.add(-1, -1, -1, -dw)
            val max = max.add(1, 1, 1, dw)

            for (x in min.x..max.x) {
                for (y in min.y..max.y) {
                    for (z in min.z..max.z) {
                        for (w in min.w..max.w) {
                            val v = Vector(x, y, z, w)

                            val active = cubes.contains(v)
                            val neighbours = countNeighbours(v)

                            val nextActive = when {
                                active && (neighbours == 2 || neighbours == 3) -> true
                                !active && neighbours == 3 -> true
                                else -> false
                            }

                            if (nextActive) {
                                nextCubes += v
                            }
                        }
                    }
                }
            }

            return Grid(min, max, nextCubes)
        }

        fun nextPart1(): Grid {
            return next(false)
        }

        fun nextPart2(): Grid {
            return next(true)
        }

        fun countActive(): Int {
            return cubes.size
        }

        companion object {
            fun parse(input: List<String>): Grid {
                require(input.isNotEmpty())

                val width = input.first().length
                val height = input.size
                val cubes = mutableSetOf<Vector>()

                for ((y, line) in input.withIndex()) {
                    for ((x, char) in line.withIndex()) {
                        when (char) {
                            '#' -> cubes += Vector(x, y, 0, 0)
                            '.' -> Unit
                            else -> throw IllegalArgumentException()
                        }
                    }
                }

                return Grid(Vector(0, 0, 0, 0), Vector(width - 1, height - 1, 0, 0), cubes)
            }
        }
    }

    override fun parse(input: Sequence<String>): Grid {
        return Grid.parse(input.toList())
    }

    override fun solvePart1(input: Grid): Int {
        var grid = input
        for (i in 0 until 6) {
            grid = grid.nextPart1()
        }
        return grid.countActive()
    }

    override fun solvePart2(input: Grid): Int {
        var grid = input
        for (i in 0 until 6) {
            grid = grid.nextPart2()
        }
        return grid.countActive()
    }
}
