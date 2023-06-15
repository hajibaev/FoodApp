package com.example.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.cache.models.FoodStorageCache
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodStorageDao {

    @Query("select * from FOOD_STORAGE_TABLE")
    fun getALlBasket(): Flow<List<FoodStorageCache>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFoodsForBasket(dishes: FoodStorageCache)

    @Query("DELETE FROM FOOD_STORAGE_TABLE WHERE id = :id")
    fun deleteById(id: Int)

}