package com.example.cafetestapp.models.adapter_models

import com.example.cafetestapp.ui.screen_dishes.listeners.TagsItemClickListener
import com.example.common_api.base.adapter.Item

data class TagsAdapterModel(
    var tags: List<String>,
    val listener: TagsItemClickListener
) : Item