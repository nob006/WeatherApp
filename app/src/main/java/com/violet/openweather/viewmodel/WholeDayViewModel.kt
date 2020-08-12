package com.violet.openweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.violet.openweather.repositoty.WeatherRepository
import kotlinx.coroutines.launch

class WholeDayViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    fun getWeatherWholeDay(){
        viewModelScope.launch {
            val res = weatherRepository.getWeatherWholeDay("33.441792","-94.037689")
            if(res.isSuccessful){

            }

        }
    }
}