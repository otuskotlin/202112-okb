import androidx.compose.runtime.*
import models.CatModel
import models.CatViewsSelector
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import repository.CatRepositoryStub
import style.AppStyle
import views.AddCatInput
import views.ButtonPanel
import views.CatCard

fun main() {
    val repository = CatRepositoryStub()
//    val repository = CatRepositoryStub()
    renderComposable(rootElementId = "root") {
        Style(AppStyle)
        val cats = remember { mutableStateListOf(CatModel.NONE) }
        val fullImage = remember { mutableStateOf("") }
        val viewSelector = remember { mutableStateOf(CatViewsSelector.NONE) }

        H3 { Text("Надо больше котиков") }
        ButtonPanel(viewsSelector = viewSelector, repository = repository, cats = cats)

        when(viewSelector.value) {
            CatViewsSelector.NONE -> {
                fullImage.value = ""
                cats.clear()
            }
            CatViewsSelector.LIST -> {
                for (cat in cats) {
                    CatCard(cat, fullImage)
                }
                if (fullImage.value.isNotBlank()) {
                    Div {
                        Img(
                            src = fullImage.value
                        )
                    }
                }
            }
            CatViewsSelector.ADD -> {
                AddCatInput(repository = repository)
            }
        }

    }
}

