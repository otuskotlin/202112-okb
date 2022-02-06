package ru.otus.otuskotlin.dsl.simple

/**
 * Simple examples for using dsl
 * https://pl.kotl.in/JgQTAYUes
 */

fun sout(block: () -> Any?) {
    val result = block()
    println(result)
}

class MyContext {
    fun time() = System.currentTimeMillis()
}

fun soutWithPrefix(block: MyContext.() -> Any?) {
    val context = MyContext()
    val result = block(context)
    println(result)
}

fun main() {
    sout {
        "my" + '-' + " line"
    }

    soutWithPrefix {
        // this.time() --> time()
        "time is " + time() + "."
    }
}
