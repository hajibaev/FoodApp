package com.example.cafetestapp.di

import com.example.common_api.BaseResourceProvider
import com.example.common_api.DispatchersProvider
import com.example.common_impl.DispatchersProviderImpl
import com.example.data.base.ResponseHandler
import com.example.data.base.ResponseHandlerImpl
import org.koin.dsl.module


val appModule = module {

    single<ResponseHandler> { ResponseHandlerImpl(dispatchersProvider = get()) }

    single<DispatchersProvider> { DispatchersProviderImpl() }

    single<BaseResourceProvider> { BaseResourceProvider.Base(context = get()) }

}