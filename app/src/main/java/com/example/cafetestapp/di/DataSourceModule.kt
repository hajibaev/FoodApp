package com.example.cafetestapp.di

import com.example.data.cache.mappers.MapFromCategoryCacheToData
import com.example.data.cache.mappers.MapFromCategoryDataToCache
import com.example.data.cache.mappers.MapFromDishesCacheToData
import com.example.data.cache.mappers.MapFromDishesDataToCache
import com.example.data.cache.mappers.MapFromDishesDataToFoodStorageCache
import com.example.data.cache.mappers.MapFromFoodStorageCacheToData
import com.example.data.cache.source.category.CategoryCacheDataSource
import com.example.data.cache.source.category.CategoryCacheDataSourceImpl
import com.example.data.cache.source.dishes.DishesCacheDataSource
import com.example.data.cache.source.dishes.DishesCacheDataSourceImpl
import com.example.data.cache.source.food_storage.FoodStorageCacheDataSource
import com.example.data.cache.source.food_storage.FoodStorageCacheDataSourceImpl
import com.example.data.cloud.mappers.MapFromCategoryCloudToData
import com.example.data.cloud.mappers.MapFromDishesCloudToData
import com.example.data.cloud.mappers.MapFromDishesResponseToCloud
import com.example.data.cloud.source.category.CategoryDataSource
import com.example.data.cloud.source.category.CategoryDataSourceImpl
import com.example.data.cloud.source.dishes.DishesDataSource
import com.example.data.cloud.source.dishes.DishesDataSourceImpl
import org.koin.dsl.module


val dataSourceModule = module {

    /**
     * CloudDataSource
     */

    single<CategoryDataSource> {
        CategoryDataSourceImpl(
            service = get(),
            mapCategoryCloudToData = MapFromCategoryCloudToData(),
            dispatchersProvider = get()
        )
    }

    single<DishesDataSource> {
        DishesDataSourceImpl(
            service = get(),
            dispatchersProvider = get(),
            responseHandler = get(),
            mapFromDishesResponseToCloud = MapFromDishesResponseToCloud(),
            mapDishesCloud = get(),
            mapFromDishesCloudToData = MapFromDishesCloudToData()
        )
    }

    /**
     * CacheDataSource
     */

    single<CategoryCacheDataSource> {
        CategoryCacheDataSourceImpl(
            dao = get(),
            dispatchersProvider = get(),
            categoryCacheToDataMapper = MapFromCategoryCacheToData(),
            categoryDataToCacheMapper = MapFromCategoryDataToCache(),
        )
    }


    single<DishesCacheDataSource> {
        DishesCacheDataSourceImpl(
            dao = get(),
            dispatchersProvider = get(),
            mapFromDishesCacheToData = MapFromDishesCacheToData(),
            mapFromDishesDataToCache = MapFromDishesDataToCache()
        )
    }

    single<FoodStorageCacheDataSource> {
        FoodStorageCacheDataSourceImpl(
            dao = get(),
            dispatchers = get(),
            mapFromFoodStorageCacheToData = MapFromFoodStorageCacheToData(),
            mapFromDishesDataToCache = MapFromDishesDataToFoodStorageCache()
        )
    }


}