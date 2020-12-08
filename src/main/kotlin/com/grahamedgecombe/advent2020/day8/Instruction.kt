package com.grahamedgecombe.advent2020.day8

data class Instruction(val opcode: Opcode, val argument: Int) {
    companion object {
        fun parse(s: String): Instruction {
            val parts = s.split(" ", limit = 2)
            require(parts.size == 2)

            val opcode = Opcode.fromString(parts[0]) ?: throw IllegalArgumentException()
            val argument = parts[1].toInt()

            return Instruction(opcode, argument)
        }
    }
}
