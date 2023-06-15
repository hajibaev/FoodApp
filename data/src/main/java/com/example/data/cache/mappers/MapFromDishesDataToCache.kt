package com.example.data.cache.mappers

import com.example.common_api.Mapper
import com.example.data.cache.models.DishesCache
import com.example.data.data.models.DishesData

class MapFromDishesDataToCache : Mapper<DishesData, DishesCache> {
    override fun map(from: DishesData) = from.run {
        DishesCache(
            id = dishes_id,
            name = dishes_name,
            price = price,
            weight = weight,
            description = description,
            tegs = tegs,
            imageUrl = image_url
        )
    }
}