package models

    data class CatModel(
        val id: String = "",
        val name: String = "",
        val ageMonth: Int = Int.MIN_VALUE,
        val imgSrc: String = "",
    )
