package com.grahamedgecombe.advent2020.day18

import com.grahamedgecombe.advent2020.Puzzle

object Day18 : Puzzle<List<String>>(18) {
    sealed class Expr {
        data class Literal(val value: Long) : Expr()
        data class Add(val left: Expr, val right: Expr) : Expr()
        data class Multiply(val left: Expr, val right: Expr) : Expr()
    }

    private class TokenStream(s: String) {
        private val tokens = s.split(DELIMITERS).map(String::trim).filter(String::isNotEmpty)
        private var index = 0

        fun peek(): String? {
            return if (index < tokens.size) {
                tokens[index]
            } else {
                null
            }
        }

        fun consume(): String? {
            val token = peek()
            index++
            return token
        }

        private companion object {
            private val DELIMITERS = Regex("((?<=[()])|(?=[()])|\\s)")
        }
    }

    private fun parseExpr(tokens: TokenStream): Expr {
        var expr = parseValue(tokens)

        while (tokens.peek() == "+" || tokens.peek() == "*") {
            val op = tokens.consume()
            expr = when (op) {
                "+" -> Expr.Add(expr, parseValue(tokens))
                "*" -> Expr.Multiply(expr, parseValue(tokens))
                else -> throw AssertionError()
            }
        }

        return expr
    }

    private fun parseValue(tokens: TokenStream): Expr {
        val token = tokens.consume()
        check(token != null)

        val value = token.toLongOrNull()
        if (value != null) {
            return Expr.Literal(value)
        }

        check(token == "(")
        val expr = parseExpr(tokens)
        check(tokens.consume() == ")")

        return expr
    }

    private fun parse(s: String): Expr {
        val tokens = TokenStream(s)
        val expr = parseExpr(tokens)
        check(tokens.peek() == null)
        return expr
    }

    private fun evaluate(expr: Expr): Long {
        return when (expr) {
            is Expr.Literal -> expr.value
            is Expr.Add -> evaluate(expr.left) + evaluate(expr.right)
            is Expr.Multiply -> evaluate(expr.left) * evaluate(expr.right)
        }
    }

    fun evaluatePart1(s: String): Long {
        return evaluate(parse(s))
    }

    override fun parse(input: Sequence<String>): List<String> {
        return input.toList()
    }

    override fun solvePart1(input: List<String>): Long {
        return input.map(::evaluatePart1).sum()
    }
}
