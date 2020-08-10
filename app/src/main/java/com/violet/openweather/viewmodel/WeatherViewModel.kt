package com.violet.openweather.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.violet.openweather.repositoty.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    fun getWeatherByCity(){
        viewModelScope.launch {
            val res = weatherRepository.getWeatherByCity()
            Log.d("DEV" , res.body()?.name ?: "")
        }
    }
}