package com.example.data.cloud.source.dishes

import android.util.Log
import com.example.common_api.DispatchersProvider
import com.example.common_api.Mapper
import com.example.data.base.ResponseHandler
import com.example.data.cloud.CloudDataRequestState
import com.example.data.cloud.models.DishesCloud
import com.example.data.cloud.models.DishesCloudDataMapper
import com.example.data.cloud.models.DishesResponseCloud
import com.example.data.cloud.service.DishesService
import com.example.data.data.models.DishesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class DishesDataSourceImpl(
    private val service: DishesService,
    private val responseHandler: ResponseHandler,
    private val dispatchersProvider: DispatchersProvider,
    private val mapFromDishesCloudToData: Mapper<DishesCloud, DishesData>,
    private val mapDishesCloud: DishesCloudDataMapper,
    private val mapFromDishesResponseToCloud: Mapper<DishesResponseCloud, DishesCloud>
) : DishesDataSource {

    override fun fetchDishes(): Flow<List<DishesData>> = flow {
        emit(service.fetchAllDishes())
        Log.i("hajibaev", "DishesDataSourceImpl = fetchDishes()")
    }.flowOn(dispatchersProvider.io())
        .map { it.body() ?: DishesResponseCloud(emptyList()) }
        .map { it.dishes }
        .map { dishes -> dishes.map(mapFromDishesCloudToData::map) }
        .flowOn(dispatchersProvider.default())

    override suspend fun fetchDishesById(id: Int): CloudDataRequestState<DishesData> =
        responseHandler.safeApiCall { service.fetchAllDishes() }
            .map(mapFromDishesResponseToCloud)
            .map(mapDishesCloud.map())


}