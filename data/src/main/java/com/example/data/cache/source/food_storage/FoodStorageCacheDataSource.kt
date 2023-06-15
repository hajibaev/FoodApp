package com.example.data.cache.source.food_storage

import com.example.data.data.models.DishesData
import kotlinx.coroutines.flow.Flow

interface FoodStorageCacheDataSource {

    suspend fun save(dishes: DishesData)

    suspend fun delete(dishesId: Int)

    fun getAlleForBasket(): Flow<List<DishesData>>

}