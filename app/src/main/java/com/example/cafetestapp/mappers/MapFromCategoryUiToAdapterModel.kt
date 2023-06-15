package com.example.cafetestapp.mappers

import com.example.cafetestapp.models.CategoryUiModel
import com.example.cafetestapp.models.adapter_models.CategoryAdapterModel
import com.example.common_api.Mapper

class MapFromCategoryUiToAdapterModel : Mapper<CategoryUiModel, CategoryAdapterModel> {
    override fun map(from: CategoryUiModel) = from.run {
        CategoryAdapterModel(
            id = categoryId,
            title = category_name,
            posterUrl = category_image,
        )
    }
}