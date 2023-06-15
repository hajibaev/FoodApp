package com.example.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.cache.models.DishesCache
import kotlinx.coroutines.flow.Flow

@Dao
interface DishesDao {

    @Query("select * from dishes_table")
    fun fetchAllDishesObservable(): Flow<MutableList<DishesCache>>

    @Query("select * from dishes_table")
    suspend fun fetchAllDishesSingle(): MutableList<DishesCache>

    @Query("select * from dishes_table where id == :id")
    fun fetchDishesFromId(id: Int): Flow<DishesCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewDishes(food: DishesCache)

    @Query("DELETE FROM dishes_table")
    fun clearTable()
}