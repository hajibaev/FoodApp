package com.example.data.cloud.mappers

import com.example.common_api.Mapper
import com.example.data.cloud.models.FoodCategoryCloud
import com.example.data.data.models.FoodCategoryData

class MapFromCategoryCloudToData : Mapper<FoodCategoryCloud, FoodCategoryData> {
    override fun map(from: FoodCategoryCloud) = from.run {
        FoodCategoryData(
            categoryId = categoryId,
            category_name = category_name,
            category_image = category_image
        )
    }
}