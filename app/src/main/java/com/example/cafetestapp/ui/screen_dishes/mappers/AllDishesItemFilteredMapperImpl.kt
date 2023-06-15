package com.example.cafetestapp.ui.screen_dishes.mappers

import com.example.cafetestapp.models.DishesUi
import com.example.cafetestapp.models.adapter_models.DishesAdapterModel
import com.example.cafetestapp.ui.screen_dishes.listeners.DishesItemClickListener
import com.example.common_api.Mapper
import com.example.domain.models.DishesDomain
import com.example.domain.models.DishesScreenItems

class AllDishesItemFilteredMapperImpl(
    private val mapFromDishesDomainToUi: Mapper<DishesDomain, DishesUi>,
) : AllDishesItemFilteredMapper {

    override fun map(
        items: DishesScreenItems,
        filterTags: String,
        dishesItemClickListener: DishesItemClickListener,
    ): List<DishesAdapterModel> {

        val allFilteredDishesList = items.dishes.map(mapFromDishesDomainToUi::map)

        val filteredDishesItem = allFilteredDishesList.filter {
            applyAllFilteredFoods(it, tegs = filterTags)
        }.map { DishesAdapterModel(dishes = it, dishesItemClickListener) }

        return filteredDishesItem
    }


    private fun applyAllFilteredFoods(dishes: DishesUi, tegs: String) =
        if (tegs.isEmpty()) dishes.tags.toString() != String()
        else dishes.tags.toString().contains(tegs, ignoreCase = true)

}