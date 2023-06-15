package com.example.cafetestapp.ui

import android.app.Application
import com.example.cafetestapp.di.appModule
import com.example.cafetestapp.di.dataSourceModule
import com.example.cafetestapp.di.interactorModule
import com.example.cafetestapp.di.mapperModule
import com.example.cafetestapp.di.repositoryModule
import com.example.cafetestapp.di.retrofitModule
import com.example.cafetestapp.di.roomModule
import com.example.cafetestapp.di.routerModule
import com.example.cafetestapp.di.viewModelModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    retrofitModule,
                    dataSourceModule,
                    repositoryModule,
                    roomModule,
                    viewModelModel,
                    mapperModule,
                    interactorModule,
                    routerModule
                )
            )
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}