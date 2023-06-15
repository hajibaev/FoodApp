package com.example.domain.use_case

import com.example.domain.models.DishesDomain
import com.example.domain.models.DishesScreenItems
import com.example.domain.repository.DishesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


interface FetchAllDishesScreenItemsUseCase {


    operator fun invoke(): Flow<DishesScreenItems>

    operator fun invoke(id: String): Flow<DishesDomain>

}

class FetchAllDishesScreenItemsUseCaseImpl(private val repository: DishesRepository) :
    FetchAllDishesScreenItemsUseCase {

    override fun invoke(): Flow<DishesScreenItems> =
        dishesFlow.map { DishesScreenItems(it) }

    override fun invoke(id: String): Flow<DishesDomain> =
        repository.fetchDishesObservable(id = id.toInt())
            .flowOn(Dispatchers.IO)

    private val dishesFlow = repository.fetchDishes().flowOn(Dispatchers.IO)

}
