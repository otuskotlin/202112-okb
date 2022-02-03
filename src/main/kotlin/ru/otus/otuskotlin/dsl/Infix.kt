package ru.otus.otuskotlin.dsl


// Custom Infix
// All in once - https://pl.kotl.in/3fuRIEH1y
////////////////////////////////////////////////////////////////////////////////
// Remove passed chars from string

// https://pl.kotl.in/bE5OaUFHD
fun removeFoo(initial: String, char: Char): String {
    return initial.filter { it != char }
}

// https://pl.kotl.in/Uyy46FHgz
fun String.removeExt(char: Char): String {
    return this.filter { it != char }
}

// https://pl.kotl.in/Oijka4L4_
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

