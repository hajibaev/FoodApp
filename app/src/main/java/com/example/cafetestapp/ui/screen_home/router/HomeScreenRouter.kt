package com.example.cafetestapp.ui.screen_home.router

import com.example.common_api.navigation.NavCommand

interface HomeScreenRouter {

    fun navigateToDishesFragment(title: String): NavCommand
}