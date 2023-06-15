package com.example.cafetestapp.ui.screen_dishes.mappers

import com.example.cafetestapp.models.adapter_models.DishesAdapterModel
import com.example.cafetestapp.ui.screen_dishes.listeners.DishesItemClickListener
import com.example.cafetestapp.ui.screen_dishes.listeners.TagsItemClickListener
import com.example.common_api.base.adapter.Item
import com.example.domain.models.DishesScreenItems

interface AllDishesItemFilteredMapper {

    fun map(
        items: DishesScreenItems,
        filterTags: String,
        dishesItemClickListener: DishesItemClickListener,
    ): List<DishesAdapterModel>
}