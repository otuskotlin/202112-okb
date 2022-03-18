package style

import org.jetbrains.compose.web.css.*

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
            }
            minWidth(200.px)
            maxWidth(450.px)
        }

        val cardDescriptionDiv by style {
            display(DisplayStyle.InlineBlock) // Применяется к элементам класса cardDiv
        }

        val cardImage by style {
            width(150.px)
            height(150.px)
            margin(3.px)
        }
     }
