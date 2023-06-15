package com.example.common_api.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import kotlinx.parcelize.Parcelize

@Parcelize
data class NavCommand(
    @IdRes val resId: Int,
    val args: Bundle,
) : Parcelable

fun NavDirections.toNavCommand(): NavCommand {
    return NavCommand(actionId, arguments)
}