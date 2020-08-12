package com.violet.openweather.model.wholeday

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)