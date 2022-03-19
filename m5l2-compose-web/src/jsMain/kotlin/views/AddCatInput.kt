package views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.browser.window
import models.CatModel
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.flexWrap
import org.jetbrains.compose.web.dom.*
import repository.ICatRepository
import style.AppStyle
import kotlin.random.Random

/**
 * Форма и кнопка "Submit" используются для примера отправки POST-запроса
 * Предполагается использовать репозиторий для связи с сервером
 */
@Composable
fun AddCatInput(
    repository: ICatRepository
) {
    val catName = remember { mutableStateOf("") }
    val catAge = remember { mutableStateOf("") }
    val catImage = remember { mutableStateOf("") }

    Div({
        classes(AppStyle.addCardDiv)
    }) {
//        Form(action = "http://localhost:8080/cats/add", attrs = {
//            method(FormMethod.Post)
//        }){
            CatInputRow(catName, "Name: ", name = "name")
            CatInputRow(catAge, "Age in months: ", name = "age")
            CatInputRow(catImage, "URL of image: ", name = "image")
            Button({
                onClick {
                    val cat = CatModel(
                        id = Random.nextLong().toString(),
                        name = catName.value,
                        ageMonth = catAge.value.toInt(),
                        imgSrc = catImage.value,
                    )
                    repository.add(cat)
                }
            }) {
                Text("Add")
            }
//            Input(type = InputType.Submit) {
//                value("Submit")
//            }
//        }
    }
}

@Composable
fun CatInputRow(rememberValue: MutableState<String>, text: String, name: String = "") {
    Div {
        P({
            classes(AppStyle.inputCatElement)
        }){ Text(text) }
        Input(type = InputType.Text) {
            name(name)
            value(rememberValue.value)
            classes(AppStyle.inputCatElement)
            onInput { event ->
                rememberValue.value = event.value
            }
        }
    }
}
