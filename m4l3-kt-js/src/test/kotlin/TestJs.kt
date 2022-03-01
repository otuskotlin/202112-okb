import functions.Greeting
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.promise
import kotlin.js.Promise
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TestJs {

    @Test
    fun testDecamelize() {
        val camel = "otusKotlinBasic"
        val decamel = decamelize(string = camel, separator = "^")
        assertEquals("otus^kotlin^basic", decamel)
    }

    @Test
    @JsName("dynamicTest")
    fun `test of import mathjs and calculate sqrt`() {
        val result: dynamic = js("""
            var module = require("mathjs");
            module.sqrt(9);
        """)
        assertEquals(3, result)
    }

    @Test
    fun testCoroutine() = MainScope().promise {
        val greetings = Greeting("Ivan").helloWithDelay()
        assertEquals("Hello, Ivan", greetings)
    }

    @Test
    fun testExported() = MainScope().promise {
        val result = js(
            """
            var module = require("first_kt_app-m4l3-kt-js");
            var greeting = new module.functions.GreetingExport("Otus");
            return greeting.helloWithDelay();
        """
        )
        assertEquals("Hello, Otus", (result as? Promise<String>)?.await())
    }


}
