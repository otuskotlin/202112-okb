import kotlinx.browser.document
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.html.InputType
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.id
import kotlinx.html.input
import kotlinx.html.js.div
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import kotlin.coroutines.CoroutineContext

fun main() {
    MainScope().promise {
        Application().start()
    }
}

class Application: CoroutineScope {
    private val body get() = document.body!!
    private val fieldInput get() = document.getElementById("field_input") as HTMLInputElement
    private val counter get() = document.getElementById("letters_counter") as HTMLElement
    private val state by lazy { MutableStateFlow(fieldInput.value.length) }
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job

    fun start() {
        body.append.div {
             input (
                 type = InputType.text
             ) {
                 id = "field_input"
             }
            div {
                id = "letters_counter"
            }
        }
        fieldInput.value = "OTUS"
        launch {
            while (true) {
                state.update { fieldInput.value.length }
                delay(100)
            }
        }
        launch {
            state.collect {
                counter.innerText = "Введено $it символов"
                println(counter.innerText)
            }
        }

    }

}
