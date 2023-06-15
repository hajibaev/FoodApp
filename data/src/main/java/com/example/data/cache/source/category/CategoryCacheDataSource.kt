package com.example.data.cache.source.category

import com.example.data.data.models.FoodCategoryData
import kotlinx.coroutines.flow.Flow

interface CategoryCacheDataSource {

    fun fetchAllCategoryFromCacheObservable(): Flow<List<FoodCategoryData>>

    suspend fun fetchAllCategoryFromCacheSingle(): List<FoodCategoryData>

    suspend fun addNewCategoryToCache(category: FoodCategoryData)

    suspend fun fetchCategoryFromId(id: Int): FoodCategoryData

    suspend fun clearTable()
}