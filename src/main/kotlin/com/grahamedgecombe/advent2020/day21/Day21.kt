package com.grahamedgecombe.advent2020.day21

import com.grahamedgecombe.advent2020.Puzzle
import com.grahamedgecombe.advent2020.UnsolvableException

object Day21 : Puzzle<List<Day21.Food>>(21) {
    data class Food(val ingredients: Set<String>, val allergens: Set<String>) {
        companion object {
            private val REGEX = Regex("([a-z ]+) \\(contains ([a-z, ]+)\\)")

            fun parse(s: String): Food {
                val match = REGEX.matchEntire(s) ?: throw IllegalArgumentException()

                val ingredients = match.groupValues[1].splitToSequence(' ').toSet()
                val allergens = match.groupValues[2].splitToSequence(", ").toSet()

                return Food(ingredients, allergens)
            }
        }
    }

    private fun getAllergenToIngredients(input: List<Food>): Map<String, Set<String>> {
        val allergenToIngredients = mutableMapOf<String, Set<String>>()

        for (food in input) {
            for (allergen in food.allergens) {
                allergenToIngredients.compute(allergen) { _, value ->
                    if (value == null) {
                        food.ingredients
                    } else {
                        food.ingredients intersect value
                    }
                }
            }
        }

        return allergenToIngredients
    }

    override fun parse(input: Sequence<String>): List<Food> {
        return input.map(Food::parse).toList()
    }

    override fun solvePart1(input: List<Food>): Int {
        val allergenToIngredients = getAllergenToIngredients(input)

        val allIngredients = input.flatMap { it.ingredients }.toSet()
        val ingredientsWithKnownAllergen = allergenToIngredients.flatMap { it.value }.toSet()
        val ingredientsWithoutKnownAllergen = allIngredients - ingredientsWithKnownAllergen

        return ingredientsWithoutKnownAllergen.map { ingredient ->
            input.count { it.ingredients.contains(ingredient) }
        }.sum()
    }

    override fun solvePart2(input: List<Food>): String {
        val allergenToIngredients = getAllergenToIngredients(input).toMutableMap()
        val allergenToIngredient = mutableMapOf<String, String>()

        next@while (allergenToIngredient.size < allergenToIngredients.size) {
            for ((allergen, ingredients) in allergenToIngredients) {
                val ingredient = ingredients.singleOrNull()
                if (ingredient != null) {
                    allergenToIngredient[allergen] = ingredient
                    allergenToIngredients.replaceAll { _, value -> value - ingredient }
                    continue@next
                }
            }

            throw UnsolvableException()
        }

        return allergenToIngredient.toList()
            .sortedBy { it.first }
            .joinToString(",") { it.second }
    }
}
