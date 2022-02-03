package ru.otus.otuskotlin.dsl

import java.time.Instant

// Inline functions
// All in once - https://pl.kotl.in/wKWGGgzBG
////////////////////////////////////////////////////////////////////////////////
// Print start and end time

// https://pl.kotl.in/Y9duQPw2o
fun logTimestamp(task: () -> Unit) {
    println(Instant.now().toString())
    task()
    println(Instant.now().toString())
}

// Optimisation with inline
// https://pl.kotl.in/LXe5YEHhQ
inline fun logTimestampInline(task: () -> Unit) {
    println(Instant.now().toString())
    task()
    println(Instant.now().toString())
}

fun main() {
    logTimestamp {
        println("WORK")
    }
    logTimestampInline {
        println("INLINe WORK")
    }
}
