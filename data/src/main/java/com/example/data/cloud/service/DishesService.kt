package com.example.data.cloud.service

import com.example.data.cloud.models.DishesResponseCloud
import retrofit2.Response
import retrofit2.http.GET

interface DishesService {

    @GET("aba7ecaa-0a70-453b-b62d-0e326c859b3b")
    suspend fun fetchAllDishes(): Response<DishesResponseCloud>
}