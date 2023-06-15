package com.example.data.cache.mappers

import com.example.common_api.Mapper
import com.example.data.cache.models.DishesCache
import com.example.data.data.models.DishesData

class MapFromDishesCacheToData : Mapper<DishesCache, DishesData>{
    override fun map(from: DishesCache) = from.run {
        DishesData(
            dishes_id = id,
            dishes_name = name,
            price = price,
            weight = weight,
            description = description,
            tegs = tegs,
            image_url = imageUrl
        )
    }
}