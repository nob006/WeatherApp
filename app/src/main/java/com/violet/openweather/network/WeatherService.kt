package com.violet.openweather.network

import com.violet.openweather.model.WeatherRes
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
    suspend fun getWeatherWholelDay(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Response<WeatherRes>
}


//onecall?lat=33.441792&lon=-94.037689&exclude=current,minutely,daily&appid=1ba800e5bc19e46da6df882fd6e8f603