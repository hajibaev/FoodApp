package com.example.data.cloud.source.category

import com.example.common_api.DispatchersProvider
import com.example.common_api.Mapper
import com.example.data.cloud.models.FoodCategoryCloud
import com.example.data.cloud.models.FoodCategoryResponseCloud
import com.example.data.cloud.service.FoodCategoryService
import com.example.data.data.models.FoodCategoryData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


class CategoryDataSourceImpl(
    private val service: FoodCategoryService,
    private val mapCategoryCloudToData: Mapper<FoodCategoryCloud, FoodCategoryData>,
    private val dispatchersProvider: DispatchersProvider
) : CategoryDataSource {

    override fun fetchCategories(): Flow<List<FoodCategoryData>> = flow {
        emit(service.getFoodCategory())
    }.flowOn(dispatchersProvider.io())
        .map { it.body() ?: FoodCategoryResponseCloud(emptyList()) }
        .map { it.category }
        .map { category -> category.map(mapCategoryCloudToData::map) }
        .flowOn(dispatchersProvider.default())

}