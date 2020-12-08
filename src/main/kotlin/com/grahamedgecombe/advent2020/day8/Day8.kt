package com.grahamedgecombe.advent2020.day8

import com.grahamedgecombe.advent2020.Puzzle

object Day8 : Puzzle<Program>(8) {
    override fun parse(input: Sequence<String>): Program {
        return Program.parse(input)
    }

    override fun solvePart1(input: Program): String {
        return runUntilDuplicate(input).toString()
    }

    private fun runUntilDuplicate(program: Program): Int {
        val vm = VirtualMachine(program)
        val visited = mutableSetOf<Int>()

        while (true) {
            if (!visited.add(vm.pc)) {
                return vm.acc
            }

            vm.step()
        }
    }
}
