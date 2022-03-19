package views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import models.CatModel
import models.CatViewsSelector
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import repository.ICatRepository

@Composable
fun ButtonPanel(
    viewsSelector: MutableState<CatViewsSelector>,
    repository: ICatRepository,
    cats: SnapshotStateList<CatModel>,
) {
    Div {
        Button(
            attrs = {
                onClick {
                    viewsSelector.value = CatViewsSelector.ADD
                }
            }
        ) {
            Text("Add")
        }
        Button(
            attrs = {
                onClick {
                    cats.clear()
                    cats.addAll(repository.list())
                    viewsSelector.value = CatViewsSelector.LIST
                }
            }
        ) {
            Text("List")
        }
        Button(
            attrs = {
                onClick {
                    viewsSelector.value = CatViewsSelector.NONE
                }
            }
        ) {
            Text("Clear")
        }
    }
}
