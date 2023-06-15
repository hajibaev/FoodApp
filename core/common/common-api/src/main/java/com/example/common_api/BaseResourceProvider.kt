package com.example.common_api

import android.content.Context
import androidx.annotation.StringRes
import com.example.ui_core.R
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface BaseResourceProvider {

    fun getString(@StringRes id: Int): String

    fun fetchErrorMessage(exception: Exception): String

    fun fetchErrorMessage(exception: Throwable): String

    fun fetchIdErrorMessage(exception: Throwable): IdResourceString

    class Base(private val context: Context) :
        BaseResourceProvider {

        override fun getString(id: Int) = context.getString(id)

        override fun fetchErrorMessage(exception: Exception): String {
            return when (exception) {
                is UnknownHostException -> getString(com.example.ui_core.R.string.network_error)
                is SocketTimeoutException -> getString(com.example.ui_core.R.string.network_error)
                is ConnectException -> getString(com.example.ui_core.R.string.network_error)
                else -> getString(com.example.ui_core.R.string.generic_error)
            }
        }

        override fun fetchErrorMessage(exception: Throwable): String {
            return when (exception) {
                is UnknownHostException -> getString(com.example.ui_core.R.string.network_error)
                is SocketTimeoutException -> getString(com.example.ui_core.R.string.network_error)
                is ConnectException -> getString(com.example.ui_core.R.string.network_error)
                else -> getString(com.example.ui_core.R.string.generic_error)
            }
        }

        override fun fetchIdErrorMessage(exception: Throwable): IdResourceString =
            when (exception) {
                is UnknownHostException -> IdResourceString(R.string.network_error)
                is SocketTimeoutException -> IdResourceString(R.string.network_error)
                is ConnectException -> IdResourceString(R.string.network_error)
                else -> IdResourceString(R.string.generic_error)
            }

    }
}