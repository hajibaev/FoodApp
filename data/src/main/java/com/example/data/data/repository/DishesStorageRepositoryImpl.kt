package com.example.data.data.repository

import com.example.common_api.Mapper
import com.example.data.cache.source.food_storage.FoodStorageCacheDataSource
import com.example.data.data.models.DishesData
import com.example.domain.models.DishesDomain
import com.example.domain.repository.FoodStorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DishesStorageRepositoryImpl(
    private val source: FoodStorageCacheDataSource,
    private val mapFromDishesDomainToData: Mapper<DishesDomain, DishesData>,
    private val mapFromDishesDataToDomain: Mapper<DishesData, DishesDomain>
) : FoodStorageRepository {

    override suspend fun save(dishes: DishesDomain) =
        withContext(Dispatchers.IO) {
            source.save(mapFromDishesDomainToData.map(dishes))
        }

    override suspend fun delete(dishesId: Int) = source.delete(dishesId)

    override fun getStorageDishes(): Flow<List<DishesDomain>> =
        source.getAlleForBasket().map { dishes ->
            dishes.map(mapFromDishesDataToDomain::map)
        }

}