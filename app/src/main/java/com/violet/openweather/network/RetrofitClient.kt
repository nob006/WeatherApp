package com.violet.openweather.network

import android.app.Application
import com.google.gson.GsonBuilder
import com.violet.openweather.common.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    private fun createBuilder() = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    fun <T> create(classService: Class<T>): T {
        return createBuilder().create(classService)
    }
}