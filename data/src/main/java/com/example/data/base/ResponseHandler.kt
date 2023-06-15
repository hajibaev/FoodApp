package com.example.data.base

import com.example.data.cloud.CloudDataRequestState
import retrofit2.Response

interface ResponseHandler {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>,
    ): CloudDataRequestState<T>
}