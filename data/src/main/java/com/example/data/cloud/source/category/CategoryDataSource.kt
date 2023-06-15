package com.example.data.cloud.source.category

import com.example.data.data.models.FoodCategoryData
import kotlinx.coroutines.flow.Flow

interface CategoryDataSource {

    fun fetchCategories(): Flow<List<FoodCategoryData>>
}