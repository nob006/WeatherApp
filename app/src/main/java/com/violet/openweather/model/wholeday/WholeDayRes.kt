package com.violet.openweather.model.wholeday

data class WholeDayRes(
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)