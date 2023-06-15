package com.example.data.cache.source.category

import com.example.common_api.DispatchersProvider
import com.example.common_api.Mapper
import com.example.data.cache.db.CategoriesDao
import com.example.data.cache.models.CategoryCache
import com.example.data.data.models.FoodCategoryData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class CategoryCacheDataSourceImpl(
    private val dao: CategoriesDao,
    private val dispatchersProvider: DispatchersProvider,
    private val categoryCacheToDataMapper: Mapper<CategoryCache, FoodCategoryData>,
    private val categoryDataToCacheMapper: Mapper<FoodCategoryData, CategoryCache>
) : CategoryCacheDataSource {

    override fun fetchAllCategoryFromCacheObservable(): Flow<List<FoodCategoryData>> =
        dao.fetchAllCategoryObservable()
            .flowOn(dispatchersProvider.io())
            .map { genres -> genres.map(categoryCacheToDataMapper::map) }
            .flowOn(dispatchersProvider.default())

    override suspend fun fetchAllCategoryFromCacheSingle(): List<FoodCategoryData> {
        val cachedList = dao.fetchAllCategorySingle()
        return cachedList.map(categoryCacheToDataMapper::map)
    }

    override suspend fun addNewCategoryToCache(category: FoodCategoryData) {
        dao.addNewCategory(categoryDataToCacheMapper.map(category))
    }

    override suspend fun fetchCategoryFromId(id: Int): FoodCategoryData =
        categoryCacheToDataMapper.map(dao.fetchCategoryFromId(id = id))

    override suspend fun clearTable() {
        dao.clearTable()
    }
}