package com.example.cafetestapp.models.adapter_models

import com.example.common_api.base.adapter.Item

data class CategoryAdapterModel(
    val id: Int,
    val title: String,
    val posterUrl: String,
) : Item