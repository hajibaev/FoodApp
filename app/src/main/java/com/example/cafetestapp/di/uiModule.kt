package com.example.cafetestapp.di

import com.example.cafetestapp.mappers.MapFromCategoryDomainToUiModel
import com.example.cafetestapp.mappers.MapFromCategoryUiToAdapterModel
import com.example.cafetestapp.mappers.MapFromDishesDomainToUi
import com.example.cafetestapp.mappers.MapFromDishesUiToAdapterModel
import com.example.cafetestapp.mappers.MapFromDishesUiToDomain
import com.example.cafetestapp.ui.screen_basket.FragmentBasketViewModel
import com.example.cafetestapp.ui.screen_dishes.FragmentDishesViewModel
import com.example.cafetestapp.ui.screen_food_info.FoodInfoDialogViewModel
import com.example.cafetestapp.ui.screen_home.FragmentHomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModel = module {

    viewModel {
        FragmentHomeScreenViewModel(
            categoryUseCase = get(),
            dispatchersProvider = get(),
            resourceProvider = get(),
            router = get(),
            mapFromCategoryDomainToUi = MapFromCategoryDomainToUiModel(),
            mapFromCategoryUiToAdapterModel = MapFromCategoryUiToAdapterModel()
        )
    }

    viewModel {
        FragmentDishesViewModel(
            fetchAllDishesScreenItemsUseCase = get(),
            dispatchersProvider = get(),
            resourceProvider = get(),
            allDishesItemFilteredMapper = get(),
        )
    }

    viewModel {
        FragmentBasketViewModel(
            fetchAllBasketScreenItemsUseCase = get(),
            dispatchersProvider = get(),
            resourceProvider = get(),
            mapFromDishesDomainToUi = MapFromDishesDomainToUi(),
            mapFromDishesUiToAdapterModel = MapFromDishesUiToAdapterModel(),
        )
    }

    viewModel { (id: String) ->
        FoodInfoDialogViewModel(
            id = id,
            saveFoodForBasketUseCase = get(),
            fetchAllDishesScreenItemsUseCase = get(),
            saveMapper = MapFromDishesUiToDomain(),
            mapFromDishesDomainToUi = MapFromDishesDomainToUi()
        )
    }


}