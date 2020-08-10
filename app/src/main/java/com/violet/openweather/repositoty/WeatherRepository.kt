package com.violet.openweather.repositoty

import com.violet.openweather.network.RetrofitClient
import com.violet.openweather.network.WeatherService

class WeatherRepository(
    private val weatherService: WeatherService
) {

    suspend fun getWeatherByCity() = weatherService.getWeatherByCity()
}