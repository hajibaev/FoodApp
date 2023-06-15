package com.example.data.data.mappers

import com.example.common_api.Mapper
import com.example.data.data.models.DishesData
import com.example.domain.models.DishesDomain

class MapFromDishesDataToDomain : Mapper<DishesData, DishesDomain> {
    override fun map(from: DishesData) = from.run {
        DishesDomain(
            dishes_id = dishes_id,
            dishes_name = dishes_name,
            price = price,
            weight = weight,
            description = description,
            image_url = image_url,
            tags = tegs
        )
    }
}