package repository

import models.CatModel

interface ICatRepository {
    fun add(cat: CatModel)

    fun list(): List<CatModel>

    fun delete(id: String): CatModel

    companion object {
        val NONE = object : ICatRepository {
            override fun add(cat: CatModel) {
                TODO("Not yet implemented")
            }

            override fun list(): List<CatModel> {
                TODO("Not yet implemented")
            }

            override fun delete(id: String): CatModel {
                TODO("Not yet implemented")
            }
        }
    }
}
