package com.example.domain.repository

import com.example.domain.models.DishesDomain
import kotlinx.coroutines.flow.Flow

interface DishesRepository {

    fun fetchDishes(): Flow<List<DishesDomain>>

    fun fetchDishesObservable(id: Int): Flow<DishesDomain>


}