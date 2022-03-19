package views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import models.CatModel
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text
import style.AppStyle

@Composable
fun CatCard(cat: CatModel, imgSrc: MutableState<String>) {
    Div(attrs = { classes(AppStyle.mainCardDiv)}) {
        Img(
            src = cat.imgSrc,
            attrs = {
                classes(AppStyle.cardImage)
                onClick {
                    imgSrc.value = cat.imgSrc
                }
            }
        )
        Div(
            attrs = {classes(AppStyle.cardDescriptionDiv)}
        ){
            Div {
                Text("Name: ${cat.name}")
            }
            Div {
                Text("Age: ${cat.ageMonth / 12}y ${cat.ageMonth % 12}m")
            }
        }
    }
}
