package com.example.domain.use_case

import com.example.domain.models.DishesDomain
import com.example.domain.repository.FoodStorageRepository

interface SaveFoodForBasketUseCase {

    suspend operator fun invoke(dishes: DishesDomain)

}

class SaveFoodForBasketUseCaseImpl(private val repository: FoodStorageRepository) :
    SaveFoodForBasketUseCase {

    override suspend fun invoke(dishes: DishesDomain) = repository.save(dishes)

}

