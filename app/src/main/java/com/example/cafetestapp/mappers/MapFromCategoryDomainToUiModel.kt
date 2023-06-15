package com.example.cafetestapp.mappers

import com.example.cafetestapp.models.CategoryUiModel
import com.example.common_api.Mapper
import com.example.domain.models.CategoryDomain

class MapFromCategoryDomainToUiModel : Mapper<CategoryDomain, CategoryUiModel> {
    override fun map(from: CategoryDomain) = from.run {
        CategoryUiModel(
            categoryId = categoryId,
            category_name = category_name,
            category_image = category_image
        )
    }
}