package com.example.data.cloud.service

import com.example.data.cloud.models.DishesResponseCloud
import retrofit2.Response
import retrofit2.http.GET

interface DishesService {

    @GET("aba7ecaa-0a70-453b-b62d-0e326c859b3b")
//    @GET("c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun fetchAllDishes(): Response<DishesResponseCloud>
}