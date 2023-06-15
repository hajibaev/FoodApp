package com.example.data.cache.source.food_storage

import com.example.common_api.DispatchersProvider
import com.example.common_api.Mapper
import com.example.data.cache.db.FoodStorageDao
import com.example.data.cache.models.FoodStorageCache
import com.example.data.data.models.DishesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FoodStorageCacheDataSourceImpl(
    private val dao: FoodStorageDao,
    private val dispatchers: DispatchersProvider,
    private val mapFromDishesDataToCache: Mapper<DishesData, FoodStorageCache>,
    private val mapFromFoodStorageCacheToData: Mapper<FoodStorageCache, DishesData>
) : FoodStorageCacheDataSource {


    override suspend fun save(dishes: DishesData) =
        withContext(Dispatchers.IO) {
            dao.saveFoodsForBasket(mapFromDishesDataToCache.map(dishes))
        }

    override suspend fun delete(dishesId: Int) =
        withContext(dispatchers.io()) {
            dao.deleteById(dishesId)
        }

    override fun getAlleForBasket(): Flow<List<DishesData>> =
        dao.getALlBasket()
            .flowOn(dispatchers.io())
            .map { dishes -> dishes.map(mapFromFoodStorageCacheToData::map) }
            .flowOn(dispatchers.default())

}
