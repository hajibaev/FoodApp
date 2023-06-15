package com.example.domain.use_case

import com.example.domain.models.DishesDomain
import com.example.domain.repository.FoodStorageRepository
import kotlinx.coroutines.flow.Flow

interface FetchAllBasketScreenItemsUseCase {

    suspend fun invoke(dishes: DishesDomain)

    suspend fun invoke(dishesId: Int)

    fun invoke(): Flow<List<DishesDomain>>

}

class FetchAllBasketScreenItemsUseCaseImpl(private val repository: FoodStorageRepository) :
    FetchAllBasketScreenItemsUseCase {

    override suspend fun invoke(dishes: DishesDomain) = repository.save(dishes = dishes)

    override suspend fun invoke(dishesId: Int) = repository.delete(dishesId = dishesId)

    override fun invoke(): Flow<List<DishesDomain>> = repository.getStorageDishes()
}