package ru.otus.otuskotlin.homework

fun generateNumbers() = (0..10000).map {
    (0..100).random()
}