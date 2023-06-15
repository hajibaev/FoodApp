package com.example.cafetestapp.di

import com.example.domain.use_case.CategoryUseCase
import com.example.domain.use_case.CategoryUseCaseImpl
import com.example.domain.use_case.FetchAllBasketScreenItemsUseCase
import com.example.domain.use_case.FetchAllBasketScreenItemsUseCaseImpl
import com.example.domain.use_case.FetchAllDishesScreenItemsUseCase
import com.example.domain.use_case.FetchAllDishesScreenItemsUseCaseImpl
import com.example.domain.use_case.SaveFoodForBasketUseCase
import com.example.domain.use_case.SaveFoodForBasketUseCaseImpl
import org.koin.dsl.module

val interactorModule = module {

    factory<CategoryUseCase> {
        CategoryUseCaseImpl(repository = get())
    }

    factory<FetchAllDishesScreenItemsUseCase> {
        FetchAllDishesScreenItemsUseCaseImpl(repository = get())
    }

    factory<SaveFoodForBasketUseCase> {
        SaveFoodForBasketUseCaseImpl(repository = get())
    }

    factory<FetchAllBasketScreenItemsUseCase> {
        FetchAllBasketScreenItemsUseCaseImpl(repository = get())
    }

}