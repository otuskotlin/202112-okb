package ru.otus.otuskotlin.dsl

import kotlin.random.Random

// Simple extension to random
////////////////////////////////////////////////////////////////////////////////

/**
 * Generate random pair of ints
 */
fun Random.nextPair(): Pair<Int, Int> {
    val a = this.nextInt()
    val b = this.nextInt()

    return a to b
}

////////////////////////////////////////////////////////////////////////////////


fun main() {
    val rand = Random.Default

    val num = rand.nextPair()

    println(num)
}
