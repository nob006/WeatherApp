package com.violet.openweather.network

import com.violet.openweather.model.WeatherRes
import retrofit2.Response
import retrofit2.http.GET

interface WeatherService {
    @GET("weather?q=bangkok&appid=1ba800e5bc19e46da6df882fd6e8f603&lang=th")
    suspend fun getWeatherByCity(): Response<WeatherRes>
}