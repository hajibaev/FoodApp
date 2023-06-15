package com.example.data.cloud.service

import com.example.data.cloud.models.FoodCategoryResponseCloud
import retrofit2.Response
import retrofit2.http.GET


interface FoodCategoryService {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getFoodCategory(
    ): Response<FoodCategoryResponseCloud>


}