package ru.otus.otuskotlin.dsl

import kotlin.random.Random

// Simple lambda
////////////////////////////////////////////////////////////////////////////////

/**
 * Print random number
 */
fun printRandomNumberFoo() {
    val num = Random.nextInt()

    println(num)
}

val printRandomNumber = {
    val num = Random.nextInt()

    println(num)
}

// Lambda with in arg
////////////////////////////////////////////////////////////////////////////////

fun printRandomNumberFooWithSeed(seed: Int) {
    val num = Random(seed).nextInt()

    println(num)
}

val printRandomNumberWithSeed: (seed: Int) -> Unit = {
    val num = Random(it).nextInt()

    println(num)
}

// Lambda with in lambda
////////////////////////////////////////////////////////////////////////////////

fun printNumberFromFoo(customFoo: () -> Number) {
    val num = customFoo()

    println(num)
}

fun printNumberFromFooWithModifier2(customFoo: (Int) -> Number) {
    val num = customFoo(2)

    println(num)
}

fun main() {
    // Default lambda
    printRandomNumberFoo()

    printRandomNumber()

    // Lambda with values
    printRandomNumberFooWithSeed(123)

    printRandomNumberWithSeed(123)

    // Lambda as param
    printNumberFromFoo {
        1 + 1
    }
    printNumberFromFooWithModifier2 {
        it * (1 + 1)
    }
}
