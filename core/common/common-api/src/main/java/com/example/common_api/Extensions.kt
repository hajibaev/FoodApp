package com.example.common_api

import androidx.navigation.NavController
import com.example.common_api.navigation.NavCommand


/**
 * NavControllerExtension
 */
fun NavController.navigateTo(navCommand: NavCommand) {
    navigate(navCommand.resId, navCommand.args)
}
