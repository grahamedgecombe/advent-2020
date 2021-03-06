package com.grahamedgecombe.advent2020.day14

import com.grahamedgecombe.advent2020.Puzzle

object Day14 : Puzzle<List<Day14.Instruction>>(14) {
    sealed class Instruction {
        data class Mask(
            val zeroes: Long,
            val ones: Long,
            val floatingBits: Set<Int>
        ) : Instruction() {
            fun maskValue(value: Long): Long {
                return (value or ones) and zeroes.inv()
            }

            fun maskAddress(value: Long): Set<Long> {
                var values = setOf(value or ones)

                for (bit in floatingBits) {
                    val nextValues = mutableSetOf<Long>()
                    for (v in values) {
                        nextValues += v or (1L shl bit)
                        nextValues += v and (1L shl bit).inv()
                    }
                    values = nextValues
                }

                return values
            }
        }
        data class Write(val address: Long, val value: Long) : Instruction()

        companion object {
            private val MASK_REGEX = Regex("mask = ([X01]{36})")
            private val WRITE_REGEX = Regex("mem\\[(\\d+)] = (\\d+)")

            fun parse(s: String): Instruction {
                var match = MASK_REGEX.matchEntire(s)
                if (match != null) {
                    var zeroes = 0L
                    var ones = 0L
                    val floating = mutableSetOf<Int>()

                    for ((i, char) in match.groupValues[1].withIndex()) {
                        val bit = 35 - i
                        when (char) {
                            '0' -> zeroes = zeroes or (1L shl bit)
                            '1' -> ones = ones or (1L shl bit)
                            'X' -> floating += bit
                            else -> throw IllegalArgumentException()
                        }
                    }

                    return Mask(zeroes, ones, floating)
                }

                match = WRITE_REGEX.matchEntire(s) ?: throw IllegalArgumentException(s)

                val address = match.groupValues[1].toLong()
                val value = match.groupValues[2].toLong()

                return Write(address, value)
            }
        }
    }

    override fun parse(input: Sequence<String>): List<Instruction> {
        return input.map(Instruction::parse).toList()
    }

    override fun solvePart1(input: List<Instruction>): Long {
        val memory = mutableMapOf<Long, Long>()
        lateinit var mask: Instruction.Mask

        for (insn in input) {
            when (insn) {
                is Instruction.Mask -> mask = insn
                is Instruction.Write -> memory[insn.address] = mask.maskValue(insn.value)
            }
        }

        return memory.values.sum()
    }

    override fun solvePart2(input: List<Instruction>): Long {
        val memory = mutableMapOf<Long, Long>()
        lateinit var mask: Instruction.Mask

        for (insn in input) {
            when (insn) {
                is Instruction.Mask -> mask = insn
                is Instruction.Write -> {
                    for (address in mask.maskAddress(insn.address)) {
                        memory[address] = insn.value
                    }
                }
            }
        }

        return memory.values.sum()
    }
}
