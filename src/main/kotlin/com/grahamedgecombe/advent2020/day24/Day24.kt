package com.grahamedgecombe.advent2020.day24

import com.grahamedgecombe.advent2020.Puzzle
import kotlin.math.max
import kotlin.math.min

object Day24 : Puzzle<List<List<Day24.Direction>>>(24) {
    enum class Direction {
        EAST,
        SOUTH_EAST,
        SOUTH_WEST,
        WEST,
        NORTH_WEST,
        NORTH_EAST;

        companion object {
            fun fromChar(char: Char): Direction {
                return when (char) {
                    'e' -> EAST
                    'w' -> WEST
                    else -> throw IllegalArgumentException()
                }
            }

            fun fromChars(char1: Char, char2: Char): Direction {
                return when {
                    char1 == 's' && char2 == 'e' -> SOUTH_EAST
                    char1 == 's' && char2 == 'w' -> SOUTH_WEST
                    char1 == 'n' && char2 == 'e' -> NORTH_EAST
                    char1 == 'n' && char2 == 'w' -> NORTH_WEST
                    else -> throw IllegalArgumentException()
                }
            }
        }
    }

    data class Position(val x: Int, val y: Int) {
        fun minimum(position: Position): Position {
            return Position(min(x, position.x), min(y, position.y))
        }

        fun maximum(position: Position): Position {
            return Position(max(x, position.x), max(y, position.y))
        }

        fun add(direction: Direction): Position {
            // odd-r (see https://www.redblobgames.com/grids/hexagons/)
            val even = y % 2 == 0
            return when (direction) {
                Direction.EAST -> Position(x + 1, y)
                Direction.WEST -> Position(x - 1, y)
                Direction.SOUTH_EAST -> Position(if (even) x else x + 1, y + 1)
                Direction.SOUTH_WEST -> Position(if (even) x - 1 else x, y + 1)
                Direction.NORTH_EAST -> Position(if (even) x else x + 1, y - 1)
                Direction.NORTH_WEST -> Position(if (even) x - 1 else x, y - 1)
            }
        }

        fun add(dx: Int, dy: Int): Position {
            return Position(x + dx, y + dy)
        }

        companion object {
            val ZERO = Position(0, 0)
        }
    }

    class Grid(
        private val min: Position,
        private val max: Position,
        private val blackTiles: Set<Position>
    ) {
        fun countBlackTiles(): Int {
            return blackTiles.size
        }

        fun next(): Grid {
            val nextBlackTiles = mutableSetOf<Position>()

            for (y in min.y - 1..max.y + 1) {
                for (x in min.x - 1..max.x + 1) {
                    val position = Position(x, y)

                    var neighbours = 0
                    for (direction in Direction.values()) {
                        if (blackTiles.contains(position.add(direction))) {
                            neighbours++
                        }
                    }

                    val black = blackTiles.contains(position)
                    val nextBlack = when {
                        black && (neighbours == 0 || neighbours > 2) -> false
                        !black && neighbours == 2 -> true
                        else -> black
                    }

                    if (nextBlack) {
                        nextBlackTiles += position
                    }
                }
            }

            return Grid(min.add(-1, -1), max.add(1, 1), nextBlackTiles)
        }

        companion object {
            fun create(input: List<List<Direction>>): Grid {
                var min = Position.ZERO
                var max = Position.ZERO
                val blackTiles = mutableSetOf<Position>()

                for (directions in input) {
                    val position = getPosition(directions)

                    min = min.minimum(position)
                    max = max.maximum(position)

                    if (blackTiles.contains(position)) {
                        blackTiles -= position
                    } else {
                        blackTiles += position
                    }
                }

                return Grid(min, max, blackTiles)
            }
        }
    }

    private fun parseDirections(s: String): List<Direction> {
        val directions = mutableListOf<Direction>()

        var i = 0
        while (i < s.length) {
            val char1 = s[i++]

            if ((char1 == 's' || char1 == 'n') && i < s.length) {
                val char2 = s[i]
                if (char2 == 'e' || char2 == 'w') {
                    i++
                    directions += Direction.fromChars(char1, char2)
                    continue
                }
            }

            directions += Direction.fromChar(char1)
        }

        return directions
    }

    private fun getPosition(directions: List<Direction>): Position {
        var position = Position.ZERO
        for (direction in directions) {
            position = position.add(direction)
        }
        return position
    }

    override fun parse(input: Sequence<String>): List<List<Direction>> {
        return input.map(::parseDirections).toList()
    }

    override fun solvePart1(input: List<List<Direction>>): Int {
        return Grid.create(input).countBlackTiles()
    }

    override fun solvePart2(input: List<List<Direction>>): Int {
        var grid = Grid.create(input)
        for (i in 0 until 100) {
            grid = grid.next()
        }
        return grid.countBlackTiles()
    }
}
