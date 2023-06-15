package com.example.cafetestapp.ui.screen_home.router

import com.example.cafetestapp.ui.screen_home.FragmentHomeScreenDirections
import com.example.common_api.navigation.NavCommand
import com.example.common_api.navigation.toNavCommand

class HomeScreenRouterImpl : HomeScreenRouter {

    override fun navigateToDishesFragment(title: String): NavCommand =
        FragmentHomeScreenDirections.actionScreenHomeToFragmentDishes(title)
            .toNavCommand()

}