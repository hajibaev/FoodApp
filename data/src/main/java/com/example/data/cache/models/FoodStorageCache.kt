package com.example.data.cache.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val FOOD_STORAGE_TABLE = "food_storage_table"

@Entity(tableName = FOOD_STORAGE_TABLE, indices = [Index("id")])
data class FoodStorageCache(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("name") val dishes_name: String,
    @SerializedName("price") val price: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("tegs") val tags: List<String>,
)