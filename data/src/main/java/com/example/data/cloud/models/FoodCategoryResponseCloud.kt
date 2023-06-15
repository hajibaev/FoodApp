package com.example.data.cloud.models

import com.google.gson.annotations.SerializedName

data class FoodCategoryResponseCloud(
    @SerializedName("сategories") val category: List<FoodCategoryCloud>
)
