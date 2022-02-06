package ru.otus.otuskotlin.dsl.simple

import junit.framework.TestCase.assertEquals
import org.junit.Test

class SimpleTestCase {
    @Test
    fun `sout without params`() {
        sout {
            1 + 413
        }
    }
    @Test
    fun `sout with prefix`() {
        soutWithPrefix {
            "${time()}: my line"
        }
    }

    @Test
    fun `HW test`() {
        println("HW")
        assertEquals(null, null)
    }
}
