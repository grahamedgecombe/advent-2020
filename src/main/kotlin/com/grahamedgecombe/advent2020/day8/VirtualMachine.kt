package com.grahamedgecombe.advent2020.day8

class VirtualMachine(private val program: Program) {
    var pc = 0
    var acc = 0

    fun step(): Boolean {
        val insn = program.instructions[pc++]
        when (insn.opcode) {
            Opcode.ACC -> acc += insn.argument
            Opcode.JMP -> pc += insn.argument - 1
            Opcode.NOP -> Unit
        }

        if (pc == program.instructions.size) {
            return false
        }

        check(pc >= 0 && pc < program.instructions.size)
        return true
    }
}
