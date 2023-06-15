package com.example.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "dishes_table", indices = [Index("id")])
class DishesCache(
    @PrimaryKey val id: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("price") val price: Int,
    @ColumnInfo("weight") val weight: Int,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("imageUrl") val imageUrl: String,
    @ColumnInfo("tegs") val tegs: List<String>,
)