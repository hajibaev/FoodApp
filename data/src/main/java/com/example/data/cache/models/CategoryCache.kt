package com.example.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

private const val CATEGORY_TABLE_NAME = "categories_table"

@Entity(tableName = CATEGORY_TABLE_NAME)
class CategoryCache(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val category_name: String,
    @ColumnInfo(name = "image_url") val category_image: String,
)