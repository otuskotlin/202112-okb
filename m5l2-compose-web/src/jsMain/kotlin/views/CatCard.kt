package views

import androidx.compose.runtime.Composable
import models.CatModel
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text
import style.AppStyle

@Composable
fun CatCard(cat: CatModel) {
    Div(attrs = { classes(AppStyle.mainCardDiv)}) {
        Img(
            src = cat.imgSrc,
            attrs = {classes(AppStyle.cardImage)}
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
