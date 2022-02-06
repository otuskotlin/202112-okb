package ru.otus.otuskotlin.dsl

// Inner lambdas
// All in once - https://pl.kotl.in/3GcAl1Sj2
// ALL ARE SAME SAMPLES, BUT DIFFERENT CODE
////////////////////////////////////////////////////////////////////////////////
// Shuffle string

fun shuffleSimple(value: String): String {
    val indexes = (0 until value.length).shuffled()
    val sb = StringBuilder()
    indexes.forEach { index ->
        sb.append(value[index])
    }
    return sb.toString()
}


fun String.shuffleFoo(): String {
    val indexes = (0 until this.length).shuffled()
    val sb = StringBuilder()
    indexes.forEach { index ->
        sb.append(this[index])
    }
    return sb.toString()
}

val shuffle: String.() -> String = {
    val indexes = (0 until this.length).shuffled()
    val sb = StringBuilder()
    indexes.forEach { index ->
        sb.append(this[index])
    }
    sb.toString()
}

fun main() {
    // Inner lambdas
    println(shuffleSimple("My simple line"))
    println("My simple line".shuffleFoo())
    println("My simple line".shuffle())
}
