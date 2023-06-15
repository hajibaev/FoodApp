package com.example.cafetestapp.di

import com.example.cafetestapp.mappers.MapFromDishesDomainToUi
import com.example.cafetestapp.ui.screen_dishes.mappers.AllDishesItemFilteredMapper
import com.example.cafetestapp.ui.screen_dishes.mappers.AllDishesItemFilteredMapperImpl
import com.example.data.cloud.models.DishesCloudDataMapper
import com.example.data.cloud.models.DishesCloudDataMapperImpl
import org.koin.dsl.module

val mapperModule = module {

    single<DishesCloudDataMapper> {
        DishesCloudDataMapperImpl()
    }

    factory<AllDishesItemFilteredMapper> {
        AllDishesItemFilteredMapperImpl(
            mapFromDishesDomainToUi = MapFromDishesDomainToUi()
        )
    }

}