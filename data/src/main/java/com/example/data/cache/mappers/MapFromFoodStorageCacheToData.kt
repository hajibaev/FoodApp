package com.example.data.cache.mappers

import com.example.common_api.Mapper
import com.example.data.cache.models.FoodStorageCache
import com.example.data.data.models.DishesData

class MapFromFoodStorageCacheToData: Mapper<FoodStorageCache, DishesData> {
    override fun map(from: FoodStorageCache) = from.run {
        DishesData(
            dishes_id = id,
            dishes_name = dishes_name,
            price = price,
            weight = weight,
            description = description,
            tegs = tags,
            image_url = image_url
        )
    }
}