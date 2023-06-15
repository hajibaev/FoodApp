package com.example.cafetestapp.models.adapter_models

import com.example.cafetestapp.models.DishesUi
import com.example.cafetestapp.ui.screen_dishes.listeners.DishesItemClickListener
import com.example.common_api.base.adapter.Item
import com.example.domain.models.DishesDomain

data class DishesAdapterModel(
    val dishes: DishesUi,
    val listener: DishesItemClickListener,
) : Item
