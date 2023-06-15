package com.example.data.cache.mappers

import com.example.common_api.Mapper
import com.example.data.cache.models.CategoryCache
import com.example.data.data.models.FoodCategoryData

class MapFromCategoryDataToCache : Mapper<FoodCategoryData, CategoryCache> {
    override fun map(from: FoodCategoryData) = from.run {
        CategoryCache(
            id = categoryId,
            category_name = category_name,
            category_image = category_image
        )
    }
}