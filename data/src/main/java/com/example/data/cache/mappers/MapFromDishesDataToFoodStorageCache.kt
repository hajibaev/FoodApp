package com.example.data.cache.mappers

import com.example.common_api.Mapper
import com.example.data.cache.models.FoodStorageCache
import com.example.data.data.models.DishesData

class MapFromDishesDataToFoodStorageCache : Mapper<DishesData, FoodStorageCache>{
    override fun map(from: DishesData) = from.run {
        FoodStorageCache(
            id = dishes_id,
            dishes_name = dishes_name,
            price = price,
            weight = weight,
            description = description,
            tags = tegs,
            image_url = image_url
        )
    }
}