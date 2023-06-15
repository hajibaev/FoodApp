package com.example.data.cloud.models

import com.google.gson.annotations.SerializedName

data class DishesResponseCloud(
    @SerializedName("dishes") val dishes: List<DishesCloud>
)

data class DishesCloud(
    @SerializedName("id") val dishes_id: Int,
    @SerializedName("name") val dishes_name: String,
    @SerializedName("price") val price: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("tegs") val tegs: List<String>,
)
