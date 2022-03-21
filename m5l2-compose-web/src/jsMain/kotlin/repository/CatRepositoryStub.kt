package repository

import StubConstants
import androidx.compose.runtime.toMutableStateMap
import models.CatModel
import kotlin.random.Random

/**
 * Стабовый репозиторий, инициализированный стабовым списком котов
 * Позволяет добавлять и удалять котов из словаря, возвращать список
 */
class CatRepositoryStub: ICatRepository {
    private val cache = StubConstants.cats.map {
        val id = Random.nextLong().toString()
        id to CatModel(
            id = id,
            name = it.key,
            imgSrc = it.value,
            ageMonth = Random.nextInt(1, 200)
        )
    }.toMutableStateMap()

    override fun add(cat: CatModel) {
        cache[cat.id] = cat
    }

    override fun list(): List<CatModel> {
        return cache.values.toList()
    }

    override fun delete(id: String): CatModel {
        return cache.remove(id)?: CatModel.NONE
    }
}
