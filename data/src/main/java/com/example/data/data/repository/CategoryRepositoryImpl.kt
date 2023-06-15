package com.example.data.data.repository

import com.example.common_api.DispatchersProvider
import com.example.common_api.Mapper
import com.example.data.cache.source.category.CategoryCacheDataSource
import com.example.data.cloud.source.category.CategoryDataSource
import com.example.data.data.models.FoodCategoryData
import com.example.domain.models.CategoryDomain
import com.example.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class CategoryRepositoryImpl(
    private val cloudDataSource: CategoryDataSource,
    private val cacheDataSource: CategoryCacheDataSource,
    private val dispatchersProvider: DispatchersProvider,
    private val mapCategoryDataToDomain: Mapper<FoodCategoryData, CategoryDomain>
) : CategoryRepository {

    override fun fetchCategories(): Flow<List<CategoryDomain>> = flow {
        emit(cacheDataSource.fetchAllCategoryFromCacheSingle())
    }.flatMapLatest { handleFetchCategoryInCache(it) }
        .map { category -> category.map(mapCategoryDataToDomain::map) }
        .flowOn(dispatchersProvider.default())

    private fun handleFetchCategoryInCache(
        cachedCategories: List<FoodCategoryData>,
    ) = if (cachedCategories.isEmpty()) cloudDataSource.fetchCategories()
        .onEach { category -> category.forEach { cacheDataSource.addNewCategoryToCache(it) } }
    else cacheDataSource.fetchAllCategoryFromCacheObservable()

    override suspend fun fetchCategoriesFromCache(category_id: Int): CategoryDomain {
        return mapCategoryDataToDomain.map(cacheDataSource.fetchCategoryFromId(id = category_id))
    }

}