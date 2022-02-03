package ru.otus.otuskotlin.dsl

import java.time.Instant

// Inline functions
////////////////////////////////////////////////////////////////////////////////

/**
 * Print start and end time
 */
fun logTimestamp(task: () -> Unit) {
    println(Instant.now().toString())
    task()
    println(Instant.now().toString())
}

// Optimisation with inline
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
