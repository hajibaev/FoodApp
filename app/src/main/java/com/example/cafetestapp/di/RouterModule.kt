package com.example.cafetestapp.di

import com.example.cafetestapp.ui.screen_home.router.HomeScreenRouter
import com.example.cafetestapp.ui.screen_home.router.HomeScreenRouterImpl
import org.koin.dsl.module

val routerModule = module {

    single<HomeScreenRouter> {
        HomeScreenRouterImpl()
    }

}