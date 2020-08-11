package com.violet.openweather.repositoty

import com.violet.openweather.common.Constant
import com.violet.openweather.common.UnitFormat
import com.violet.openweather.network.WeatherService

class WeatherRepository(private val weatherService: WeatherService) {
    suspend fun getWeatherByCity(city: String, unitFormat: UnitFormat) =
        weatherService.getWeatherByCity(city, Constant.API_KEY, unitFormat.format)
}