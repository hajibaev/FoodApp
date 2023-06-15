package com.example.cafetestapp.mappers

import com.example.cafetestapp.models.DishesUi
import com.example.common_api.Mapper
import com.example.domain.models.DishesDomain

class MapFromDishesDomainToUi : Mapper<DishesDomain, DishesUi> {
    override fun map(from: DishesDomain) = from.run {
        DishesUi(
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