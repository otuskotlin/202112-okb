package style

import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.keywords.CSSAutoKeyword
import org.jetbrains.compose.web.css.keywords.auto

object AppStyle: StyleSheet() {
        init {
            "*" style {
                color(Color("Coral")) // Применяется ко всем элементам
            }
            "button" style {
                borderRadius(3.px)  // Применяется ко всем кнопкам
            }
            "div" style {
                margin(2.px)
            }
            "h1, h2, h3" style {
                margin(10.px, 0.px, 20.px, 30.px)
            }
        }

        val mainCardDiv by style {
            border {
                style(LineStyle.Solid)
                color(Color("DarkGrey"))
                width(2.px)
                borderRadius(5.px)
            }
            maxWidth(450.px)
            display(DisplayStyle.InlineBlock)
        }

        val cardDescriptionDiv by style {
            display(DisplayStyle.TableRow) // Применяется к элементам класса cardDiv
            position(Position.Relative)
            top(0.px)
        }

        val cardImage by style {
            width(150.px)
            height(150.px)
            margin(3.px)
            borderRadius(5.px)
        }

        val addCardDiv by style {
            border {
                style(LineStyle.Solid)
                color(Color("DarkGrey"))
                width(2.px)
                borderRadius(5.px)
            }
            width(450.px)
            padding(5.px)
        }

        val inputCatElement by style {
            width(200.px)
            display(DisplayStyle.InlineBlock)
        }
     }
