@file:Suppress("PackageDirectoryMismatch")

package ru.otus.otuskotlin.dsl.builder.better

import java.util.*

/**
 * Better Kotlin-like builder implementation
 * https://pl.kotl.in/Bh6B9WK7h
 */


enum class Drink {
    WATER,
    COFFEE,
    TEA,
    JUICE,
}

abstract class Meal {
    class Breakfast(val eggs: Int, val bacon: Boolean, val drink: Drink, val title: String) : Meal()
}

class BreakfastBuilder {
    var eggs = 0
    var bacon = false
    var title = UUID.randomUUID().toString()
    var drink = Drink.WATER

    fun build(): Meal.Breakfast = Meal.Breakfast(eggs, bacon, drink, title)
}

fun main() {
    val breakfast: Meal = BreakfastBuilder().apply {
        eggs = 2
        bacon = true
        title = "Uncommon"
        drink = Drink.TEA
    }.build()
}
