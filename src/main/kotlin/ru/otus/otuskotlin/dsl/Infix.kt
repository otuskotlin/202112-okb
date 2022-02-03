package ru.otus.otuskotlin.dsl


// Custom Infix
////////////////////////////////////////////////////////////////////////////////

/**
 * Remove passed chars from string
 */
fun removeFoo(initial: String, char: Char): String {
    return initial.filter { it != char }
}

fun String.removeExt(char: Char): String {
    return this.filter { it != char }
}

infix fun String.remove(char: Char): String {
    return this.filter { it != char }
}


fun main() {
    // Sample with pairs
    val pair = Pair(1, 2)
    val pairWithInfix = 1 to 2


    // Sample with custom infix

    val base = "My very cool string!"

    // Simple solution
    println(removeFoo(base, 'e'))

    // Using extension
    println(base.removeExt('o'))

    // Using infix extension
    println(base.remove(' '))
    println(base remove ' ')
}

