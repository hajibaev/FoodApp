package com.example.domain.repository

import com.example.domain.models.DishesDomain
import kotlinx.coroutines.flow.Flow

interface FoodStorageRepository {

    suspend fun save(dishes: DishesDomain)

    suspend fun delete(dishesId: Int)

    fun getStorageDishes(): Flow<List<DishesDomain>>

}