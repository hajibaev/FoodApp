package com.example.cafetestapp.di

import com.example.data.cache.mappers.MapFromDishesCacheToData
import com.example.data.data.mappers.CategoryDataToDomainMapper
import com.example.data.data.mappers.MapFromDishesDataToDomain
import com.example.data.data.mappers.MapFromDishesDomainToData
import com.example.data.data.repository.CategoryRepositoryImpl
import com.example.data.data.repository.DishesRepositoryImpl
import com.example.data.data.repository.DishesStorageRepositoryImpl
import com.example.domain.repository.CategoryRepository
import com.example.domain.repository.DishesRepository
import com.example.domain.repository.FoodStorageRepository
import org.koin.dsl.module


val repositoryModule = module {

    single<CategoryRepository> {
        CategoryRepositoryImpl(
            cloudDataSource = get(),
            cacheDataSource = get(),
            dispatchersProvider = get(),
            mapCategoryDataToDomain = CategoryDataToDomainMapper(),
        )
    }

    single<DishesRepository> {
        DishesRepositoryImpl(
            cacheDataSource = get(),
            source = get(),
            dispatchersProvider = get(),
            mapFromDishesDataToDomain = MapFromDishesDataToDomain(),
            mapFromDishesCacheToData = MapFromDishesCacheToData()
        )
    }

    single<FoodStorageRepository> {
        DishesStorageRepositoryImpl(
            source = get(),
            mapFromDishesDomainToData = MapFromDishesDomainToData(),
            mapFromDishesDataToDomain = MapFromDishesDataToDomain(),
        )
    }

}