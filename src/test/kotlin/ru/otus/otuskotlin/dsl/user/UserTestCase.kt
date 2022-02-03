package ru.otus.otuskotlin.dsl.user

import junit.framework.TestCase.assertEquals
import org.junit.Test
import ru.otus.otuskotlin.dsl.user.dsl.contacts
import ru.otus.otuskotlin.dsl.user.dsl.user
import ru.otus.otuskotlin.dsl.user.models.Action

class UserTestCase {
    @Test
    fun `test user`() {
        val user = user {
            name {
                first = "Kirill"
                last = "Krylov"
            }
            contacts {
                email = "email@gmail.com"
                phone = "89123456789"
            }
            actions {
                add(Action.DELETE)
                add("UPDATE")

                +Action.CREATE
                +"READ"
            }
            available {
                tuesday("12:00")
                friday("21:30")
                monday("00:01")
            }
        }

        assertEquals(user.firstName, "Kirill")
        println(user)
    }
}
