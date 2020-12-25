package com.grahamedgecombe.advent2020.day12

import com.grahamedgecombe.advent2020.Puzzle
import kotlin.math.abs

object Day12 : Puzzle<List<Day12.Instruction>>(12) {
    enum class Action(private val char: Char) {
        North('N'),
        South('S'),
        East('E'),
        West('W'),
        Left('L'),
        Right('R'),
        Forward('F');

        companion object {
            private val values = values().associateBy(Action::char)

            fun fromChar(c: Char): Action? {
                return values[c]
            }
        }
    }

    data class Instruction(val action: Action, val value: Int) {
        companion object {
            fun parse(s: String): Instruction {
                require(s.isNotEmpty())

                val action = Action.fromChar(s.first()) ?: throw IllegalArgumentException()
                val value = s.substring(1).toInt()

                return Instruction(action, value)
            }
        }
    }

    data class Vector(val x: Int, val y: Int) {
        operator fun plus(v: Vector): Vector {
            return Vector(x + v.x, y + v.y)
        }

        operator fun minus(v: Vector): Vector {
            return Vector(x - v.x, y - v.y)
        }

        operator fun times(n: Int): Vector {
            return Vector(x * n, y * n)
        }

        fun rotate(angle: Int): Vector {
            var a = angle % 360
            if (a < 0) {
                a += 360
            }
            return when (a) {
                0 -> this
                90 -> Vector(y, -x)
                180 -> Vector(-x, -y)
                270 -> Vector(-y, x)
                else -> throw IllegalArgumentException()
            }
        }

        fun magnitude(): Int {
            return abs(x) + abs(y)
        }
    }

    override fun parse(input: Sequence<String>): List<Instruction> {
        return input.map(Instruction.Companion::parse).toList()
    }

    override fun solvePart1(input: List<Instruction>): Int {
        var position = Vector(0, 0)
        var heading = 90

        for (insn in input) {
            when (insn.action) {
                Action.North -> position += Vector(0, insn.value)
                Action.South -> position += Vector(0, -insn.value)
                Action.East -> position += Vector(insn.value, 0)
                Action.West -> position += Vector(-insn.value, 0)
                Action.Left -> heading -= insn.value
                Action.Right -> heading += insn.value
                Action.Forward -> position += Vector(0, insn.value).rotate(heading)
            }
        }

        return position.magnitude()
    }

    override fun solvePart2(input: List<Instruction>): Int {
        var position = Vector(0, 0)
        var waypoint = Vector(10, 1)

        for (insn in input) {
            when (insn.action) {
                Action.North -> waypoint += Vector(0, insn.value)
                Action.South -> waypoint += Vector(0, -insn.value)
                Action.East -> waypoint += Vector(insn.value, 0)
                Action.West -> waypoint += Vector(-insn.value, 0)
                Action.Left -> waypoint = waypoint.rotate(-insn.value)
                Action.Right -> waypoint = waypoint.rotate(insn.value)
                Action.Forward -> position += waypoint * insn.value
            }
        }

        return position.magnitude()
    }
}
