package com.example.common_api

interface Mapper<From, To> {

    fun map(from: From): To
}