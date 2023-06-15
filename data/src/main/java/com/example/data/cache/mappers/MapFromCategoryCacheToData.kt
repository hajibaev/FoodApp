package com.example.data.cache.mappers

import com.example.common_api.Mapper
import com.example.data.cache.models.CategoryCache
import com.example.data.data.models.FoodCategoryData

class MapFromCategoryCacheToData : Mapper<CategoryCache, FoodCategoryData> {
    override fun map(from: CategoryCache) = from.run {
        FoodCategoryData(
            categoryId = id,
            category_name = category_name,
            category_image = category_image
        )
    }
}