package com.violet.openweather.network

import com.violet.openweather.model.weather.WeatherRes
import com.violet.openweather.model.wholeday.WholeDayRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather?&lang=th")
    suspend fun getWeatherByCity(
            @Query("q") city: String,
            @Query("appid") apiKey: String,
            @Query("units") units: String
    ): Response<WeatherRes>

    @GET("onecall?exclude=current,minutely,daily&lang=th")
    suspend fun getWeatherWholeDay(
            @Query("lat") lat: String,
            @Query("lon") lon: String,
            @Query("appid") apiKey: String,
            @Query("units") units: String
    ): Response<WholeDayRes>
}
