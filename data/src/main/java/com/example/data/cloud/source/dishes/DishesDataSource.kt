package com.example.data.cloud.source.dishes

import com.example.data.cloud.CloudDataRequestState
import com.example.data.data.models.DishesData
import kotlinx.coroutines.flow.Flow

interface DishesDataSource {

    fun fetchDishes(): Flow<List<DishesData>>

    suspend fun fetchDishesById(id: Int): CloudDataRequestState<DishesData>

}