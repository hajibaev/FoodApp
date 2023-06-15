package com.example.data.data.repository

import android.util.Log
import com.example.common_api.DispatchersProvider
import com.example.common_api.Mapper
import com.example.data.cache.models.DishesCache
import com.example.data.cache.source.dishes.DishesCacheDataSource
import com.example.data.cloud.source.dishes.DishesDataSource
import com.example.data.cloud.takeSuccess
import com.example.data.data.models.DishesData
import com.example.domain.models.DishesDomain
import com.example.domain.repository.DishesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class DishesRepositoryImpl(
    private val cacheDataSource: DishesCacheDataSource,
    private val source: DishesDataSource,
    private val dispatchersProvider: DispatchersProvider,
    private val mapFromDishesDataToDomain: Mapper<DishesData, DishesDomain>,
    private val mapFromDishesCacheToData: Mapper<DishesCache, DishesData>,
) : DishesRepository {

    override fun fetchDishes(): Flow<List<DishesDomain>> = flow {
        emit(cacheDataSource.fetchAllDishesFromCacheSingle())
        Log.i("hajibaev", "DishesRepositoryImpl = fetchDishes()")
    }.flatMapLatest { handleFetchDishesInCache(it) }
        .map { foods -> foods.map(mapFromDishesDataToDomain::map) }
        .flowOn(dispatchersProvider.default())

    override fun fetchDishesObservable(id: Int): Flow<DishesDomain> =
        cacheDataSource.fetchDishesFromId(id = id).map { dishesFromCache ->
            if (dishesFromCache == null) source.fetchDishesById(id = id)
                .takeSuccess() else mapFromDishesCacheToData.map(dishesFromCache)
        }.map { it ?: DishesData.unknown() }
            .map(mapFromDishesDataToDomain::map)
            .flowOn(dispatchersProvider.default())


    private fun handleFetchDishesInCache(
        cachedDishes: List<DishesData>,
    ) = if (cachedDishes.isEmpty())
        source.fetchDishes().onEach { dishes ->
            dishes.forEach { cacheDataSource.addNewDishesToCache(it) }
            Log.i("hajibaev", "DishesRepositoryImpl = handleFetchDishesInCache() ${dishes}")
        }
    else cacheDataSource.fetchAllDishesFromCacheObservable()

}
