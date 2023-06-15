package com.example.data.data.mappers

import com.example.common_api.Mapper
import com.example.data.data.models.FoodCategoryData
import com.example.domain.models.CategoryDomain

class CategoryDataToDomainMapper : Mapper<FoodCategoryData, CategoryDomain> {
    override fun map(from: FoodCategoryData) = from.run {
        CategoryDomain(
            categoryId = categoryId,
            category_name = category_name,
            category_image = category_image
        )
    }
}