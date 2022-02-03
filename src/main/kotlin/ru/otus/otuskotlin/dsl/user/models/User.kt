package ru.otus.otuskotlin.dsl.user.models

import java.time.LocalDateTime

// Action - separate
enum class Action {
    READ,
    DELETE,
    WRITE,
    UPDATE,
    CREATE
}

// User - separate
data class User(
    val id: String,

    val firstName: String,
    val secondName: String,
    val lastName: String,

    val available: List<LocalDateTime>,
    val email: String,
    val phone: String,

    val actions: Set<Action>,
)
