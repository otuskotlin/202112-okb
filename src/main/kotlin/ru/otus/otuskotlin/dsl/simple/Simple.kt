package ru.otus.otuskotlin.dsl.simple

/**
 * Simple examples for using dsl
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
