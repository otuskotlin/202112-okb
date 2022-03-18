package functions

import kotlinx.coroutines.*

data class Greeting(
    val name: String
) {
    suspend fun helloWithDelay(): String {
        print("Hello, ")
        delay(1000)
        println(name)
        return "Hello, $name"
    }
}

@JsExport
data class GreetingExport(
    val name: String,
//    val scope: CoroutineScope
) {
    fun helloWithDelay() = MainScope().promise {
        print("Hello, ")
        delay(1000)
        println(name)
        "Hello, $name"
    }
}
