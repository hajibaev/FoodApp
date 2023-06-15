package com.example.cafetestapp.models.adapter_models

import com.example.common_api.base.adapter.Item

data class BasketAdapterModel(
    val dishes_id: Int,
    val dishes_name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val image_url: String?,
    val tags: List<String>,
//    val listener: BasketItemClickListener,
) : Item