package com.example.cafetestapp.models

enum class TegsStyle(val id: Int, var isBlue: Boolean = false) {

    ALL_MENUS(com.example.ui_core.R.string.all_menus, isBlue = true),
    SALADS(com.example.ui_core.R.string.salads),
    WITH_RICE(com.example.ui_core.R.string.with_rice),
    WITH_FISH(com.example.ui_core.R.string.with_fish);

    companion object {
        fun allTags() = listOf(ALL_MENUS, SALADS, WITH_RICE, WITH_FISH)
    }
}