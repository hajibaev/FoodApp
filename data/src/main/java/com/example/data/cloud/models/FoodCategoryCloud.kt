package com.example.data.cloud.models

import com.google.gson.annotations.SerializedName

data class FoodCategoryCloud(
    @SerializedName("id") val categoryId: Int,
    @SerializedName("name") val category_name: String,
    @SerializedName("image_url") val category_image: String
)
