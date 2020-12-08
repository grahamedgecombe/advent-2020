package com.grahamedgecombe.advent2020.day8

inline class Program(val instructions: List<Instruction>) {
    companion object {
        fun parse(input: Sequence<String>): Program {
            return Program(input.map(Instruction::parse).toList())
        }
    }
}
