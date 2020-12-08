package com.grahamedgecombe.advent2020.day8

enum class Opcode {
    ACC,
    JMP,
    NOP;

    companion object {
        private val opcodes = values().associateBy { it.name.toLowerCase() }

        fun fromString(s: String): Opcode? {
            return opcodes[s]
        }
    }
}
