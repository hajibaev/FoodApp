package com.example.cafetestapp.models


data class DishesUi(
    val dishes_id: Int,
    val dishes_name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val image_url: String,
    val tags: List<String>,
)
