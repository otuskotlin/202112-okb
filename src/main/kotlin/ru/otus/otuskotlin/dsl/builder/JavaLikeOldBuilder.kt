@file:Suppress("PackageDirectoryMismatch")

package ru.otus.otuskotlin.dsl.builder.old

import java.util.*

/**
 * Old Java-like builder implementation
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
    private var eggs = 0
    private var bacon = false
    private var title = UUID.randomUUID().toString()
    private var drink = Drink.WATER

    fun withEggs(amount: Int): BreakfastBuilder {
        this.eggs = amount
        return this
    }

    fun withBacon(): BreakfastBuilder {
        this.bacon = true
        return this
    }

    fun withTitle(title: String): BreakfastBuilder {
        this.title = title
        return this
    }

    fun withDrink(drink: Drink): BreakfastBuilder {
        this.drink = drink
        return this
    }

    fun build(): Meal.Breakfast = Meal.Breakfast(eggs, bacon, drink, title)
}

fun main() {
    val breakfastFromInstance = Meal.Breakfast(2, true,  Drink.TEA, "Standard breakfast")

    val breakfastStepByStep: Meal = BreakfastBuilder()
        .withBacon()
        .withEggs(2)
        .withTitle("Standard breakfast")
        .withDrink(Drink.TEA)
        .build()
}
