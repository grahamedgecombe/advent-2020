package com.grahamedgecombe.advent2020.day17

import com.grahamedgecombe.advent2020.Puzzle

object Day17 : Puzzle<Day17.Grid>(17) {
    data class Vector(val x: Int, val y: Int, val z: Int) {
        fun add(dx: Int, dy: Int, dz: Int): Vector {
            return Vector(x + dx, y + dy, z + dz)
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
                        if (dx == 0 && dy == 0 && dz == 0) {
                            continue
                        }

                        if (cubes.contains(v.add(dx, dy, dz))) {
                            neighbours++
                        }
                    }
                }
            }

            return neighbours
        }

        fun next(): Grid {
            val nextCubes = mutableSetOf<Vector>()

            val min = min.add(-1, -1, -1)
            val max = max.add(1, 1, 1)

            for (x in min.x..max.x) {
                for (y in min.y..max.y) {
                    for (z in min.z..max.z) {
                        val v = Vector(x, y, z)

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

            return Grid(min, max, nextCubes)
        }

        fun countActive(): Int {
            return cubes.size
        }

        companion object {
            fun parse(input: List<String>): Grid {
                check(input.isNotEmpty())

                val width = input.first().length
                val height = input.size
                val cubes = mutableSetOf<Vector>()

                for ((y, line) in input.withIndex()) {
                    for ((x, char) in line.withIndex()) {
                        when (char) {
                            '#' -> cubes += Vector(x, y, 0)
                            '.' -> Unit
                            else -> throw IllegalArgumentException()
                        }
                    }
                }

                return Grid(Vector(0, 0, 0), Vector(width - 1, height - 1, 0), cubes)
            }
        }
    }

    override fun parse(input: Sequence<String>): Grid {
        return Grid.parse(input.toList())
    }

    override fun solvePart1(input: Grid): Any {
        var grid = input
        for (i in 0 until 6) {
            grid = grid.next()
        }
        return grid.countActive()
    }
}
