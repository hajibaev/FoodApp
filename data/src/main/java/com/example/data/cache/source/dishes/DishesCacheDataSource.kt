package com.example.data.cache.source.dishes

import com.example.data.cache.models.DishesCache
import com.example.data.data.models.DishesData
import kotlinx.coroutines.flow.Flow

interface DishesCacheDataSource {

    fun fetchAllDishesFromCacheObservable(): Flow<List<DishesData>>

    suspend fun fetchAllDishesFromCacheSingle(): List<DishesData>

    suspend fun addNewDishesToCache(dishes: DishesData)

    fun fetchDishesFromId(id: Int): Flow<DishesCache?>

    suspend fun clearTable()
}