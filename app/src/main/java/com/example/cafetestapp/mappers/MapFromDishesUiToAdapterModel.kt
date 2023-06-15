package com.example.cafetestapp.mappers

import com.example.cafetestapp.models.DishesUi
import com.example.cafetestapp.models.adapter_models.BasketAdapterModel
import com.example.common_api.Mapper

class MapFromDishesUiToAdapterModel : Mapper<DishesUi, BasketAdapterModel> {
    override fun map(from: DishesUi) = from.run {
        BasketAdapterModel(
            dishes_id = dishes_id,
            dishes_name = dishes_name,
            price = price,
            weight = weight,
            description = description,
            image_url = image_url,
            tags = tags
        )
    }
}