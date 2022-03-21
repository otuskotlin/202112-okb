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
    // Репозиторий используется для доступа к данным по котам
    val repository = CatRepositoryStub()
    renderComposable(rootElementId = "root") {
        // Применение стиля из stylesheet к документу
        Style(AppStyle)
        // Блок State Holders
        val cats = remember { mutableStateListOf(CatModel.NONE) }
        val fullImage = remember { mutableStateOf("") }
        val viewSelector = remember { mutableStateOf(CatViewsSelector.NONE) }

        H3 { Text("Надо больше котиков") }

        // Панель с кнопками управления содержимым
        ButtonPanel(viewsSelector = viewSelector, repository = repository, cats = cats)

        // В зависимости от содержания селектора отрисовывается то или иное содержимое
        when(viewSelector.value) {
            // Ничего не отображается, данные сбрасываются
            CatViewsSelector.NONE -> {
                fullImage.value = ""
                cats.clear()
            }
            // Отображение списка превью котов с отрисовкой полного изображения, если путь заполнен
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
            // Отрисовка формы ввода данных нового кота
            CatViewsSelector.ADD -> {
                AddCatInput(repository = repository)
            }
        }

    }
}

