import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import models.CatModel
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import style.AppStyle
import views.CatCard
import kotlin.random.Random

fun main() {
    var count: Int by mutableStateOf(0)

    renderComposable(rootElementId = "root") {
        Style(AppStyle)
        val cats by remember { mutableStateOf(mutableListOf<CatModel>()) }
        H3 { Text("Надо больше котиков") }

        Div(attrs = { style { padding(5.px) } }) {
            Button(attrs = {
                onClick { if (count > 0) {
                    cats.removeAt(0)
                    count -= 1
                } }
            }) {
                Text("-")
            }

            Span({ style { padding(15.px) } }) {
                Text("$count")
            }

            Button(attrs = {
                onClick {
                    count += 1
                    cats.add(CatModel(
                        name = AppConstants.names.random(),
                        ageMonth = Random.nextInt(1, 150),
                        imgSrc = AppConstants.images.random()
                    ))
                }
            }) {
                Text("+")
            }
        }

        repeat(count) {
            CatCard(cats[it])
        }
    }
}

