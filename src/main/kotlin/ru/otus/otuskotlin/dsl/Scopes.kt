package ru.otus.otuskotlin.dsl

import kotlin.random.Random

// Apply
////////////////////////////////////////////////////////////////////////////////

/**
 * Concat multiple strings with StringBuilder, cause of immutable strings
 */
fun concatString() {
    val builder = StringBuilder()
    builder.append("Not")
    builder.append(" ")
    builder.append("the")
    builder.append(" ")
    builder.append("best")
    builder.append(" ")
    builder.append("Solution")

    val str = builder.toString()

    println(str)
}

fun concatStringWithApply() {
    val str = StringBuilder().apply {
        append("Very")
        append(" ")
        append("good")
        append(" ")
        append("solution!")
    }.toString()

    println(str)
}

// Let
////////////////////////////////////////////////////////////////////////////////

fun concatStringWithLet() {
    val str = StringBuilder().let {
        it.append("Somewhere")
        it.append(" ")
        it.append("very")
        it.append(" ")
        it.append("good")
        it.append(" ")
        it.append("solution!")
        it.toString()
    }

    println(str)
}

// Also
////////////////////////////////////////////////////////////////////////////////

fun concatStringWithAlso() {
    val str = StringBuilder().also {
        it.append("Almost")
        it.append(" ")
        it.append("very")
        it.append(" ")
        it.append("good")
        it.append(" ")
        it.append("solution!")
    }.toString()

    println(str)
}

// Run
////////////////////////////////////////////////////////////////////////////////

fun concatStringWithRun() {
    val str = StringBuilder().run {
        append("Very")
        append(" ")
        append("interesting")
        append(" ")
        append("solution!")
        toString()
    }

    println(str)
}


fun main() {
    // Basic
    concatString()

    // Apply
    concatStringWithApply()

    // Let
    concatStringWithLet()

    // Also
    concatStringWithAlso()

    // Run
    concatStringWithRun()
}
