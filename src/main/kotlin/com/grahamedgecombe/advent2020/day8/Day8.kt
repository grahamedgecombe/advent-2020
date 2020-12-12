package com.grahamedgecombe.advent2020.day8

import com.grahamedgecombe.advent2020.Puzzle
import com.grahamedgecombe.advent2020.UnsolvableException

object Day8 : Puzzle<Program>(8) {
    override fun parse(input: Sequence<String>): Program {
        return Program.parse(input)
    }

    override fun solvePart1(input: Program): Int {
        return runUntilDuplicate(input) ?: throw UnsolvableException()
    }

    override fun solvePart2(input: Program): Int {
        return patchAndRunUntilHalt(input) ?: throw UnsolvableException()
    }

    private enum class Result {
        InfiniteLoop,
        Halt
    }

    private fun run(vm: VirtualMachine): Result {
        val visited = mutableSetOf<Int>()

        while (true) {
            if (!visited.add(vm.pc)) {
                return Result.InfiniteLoop
            }

            if (!vm.step()) {
                return Result.Halt
            }
        }
    }

    private fun runUntilDuplicate(program: Program): Int? {
        val vm = VirtualMachine(program)
        if (run(vm) == Result.InfiniteLoop) {
            return vm.acc
        }
        return null
    }

    private fun patchAndRunUntilHalt(program: Program): Int? {
        for ((i, insn) in program.instructions.withIndex()) {
            val opcode = when (insn.opcode) {
                Opcode.JMP -> Opcode.NOP
                Opcode.NOP -> Opcode.JMP
                else -> continue
            }

            val patched = program.instructions.toMutableList()
            patched[i] = Instruction(opcode, insn.argument)

            val vm = VirtualMachine(Program(patched))
            if (run(vm) == Result.Halt) {
                return vm.acc
            }
        }

        return null
    }
}
