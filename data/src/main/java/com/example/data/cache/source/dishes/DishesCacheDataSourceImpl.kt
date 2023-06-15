package com.example.data.cache.source.dishes

import com.example.common_api.DispatchersProvider
import com.example.common_api.Mapper
import com.example.data.cache.db.DishesDao
import com.example.data.cache.models.DishesCache
import com.example.data.data.models.DishesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class DishesCacheDataSourceImpl(
    private val dao: DishesDao,
    private val dispatchersProvider: DispatchersProvider,
    private val mapFromDishesCacheToData: Mapper<DishesCache, DishesData>,
    private val mapFromDishesDataToCache: Mapper<DishesData, DishesCache>,
) : DishesCacheDataSource {

    override fun fetchAllDishesFromCacheObservable(): Flow<List<DishesData>> =
        dao.fetchAllDishesObservable()
            .flowOn(dispatchersProvider.io())
            .map { dishes -> dishes.map(mapFromDishesCacheToData::map) }
            .flowOn(dispatchersProvider.default())

    override suspend fun fetchAllDishesFromCacheSingle(): List<DishesData> {
        val cachedList = dao.fetchAllDishesSingle()
        return cachedList.map(mapFromDishesCacheToData::map)
    }

    override suspend fun addNewDishesToCache(dishes: DishesData) =
        dao.addNewDishes(mapFromDishesDataToCache.map(dishes))


    override fun fetchDishesFromId(id: Int): Flow<DishesCache> =
        dao.fetchDishesFromId(id)
            .flowOn(dispatchersProvider.io())

    override suspend fun clearTable() = dao.clearTable()
}