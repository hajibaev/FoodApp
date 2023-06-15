package com.example.data.data.models

class DishesData(
    val dishes_id: Int,
    val dishes_name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val image_url: String,
    val tegs: List<String>,
) {
    companion object {
        fun unknown() = DishesData(
            dishes_id = String().toInt(),
            dishes_name = String(),
            price = String().toInt(),
            weight = String().toInt(),
            description = String(),
            image_url = String(),
            tegs = listOf(String())
        )
    }
}