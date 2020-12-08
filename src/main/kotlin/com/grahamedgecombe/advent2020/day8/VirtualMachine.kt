package com.grahamedgecombe.advent2020.day8

class VirtualMachine(private val program: Program) {
    var pc = 0
    var acc = 0

    fun step(): Boolean {
        val insn = program.instructions[pc++]
        when (insn.opcode) {
            Opcode.ACC -> acc += insn.argument
            Opcode.JMP -> pc += insn.argument - 1
            Opcode.NOP -> { /* empty */ }
        }

        check(pc >= 0)

        return pc < program.instructions.size
    }
}
