package com.example.cafetestapp.mappers

import com.example.cafetestapp.models.DishesUi
import com.example.common_api.Mapper
import com.example.domain.models.DishesDomain

class MapFromDishesUiToDomain : Mapper<DishesUi, DishesDomain> {
    override fun map(from: DishesUi) = from.run {
        DishesDomain(
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