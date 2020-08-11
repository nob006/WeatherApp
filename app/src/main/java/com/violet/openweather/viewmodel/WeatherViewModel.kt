package com.violet.openweather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.violet.openweather.R
import com.violet.openweather.common.UnitFormat
import com.violet.openweather.model.WeatherRes
import com.violet.openweather.repositoty.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private var unitFormat = UnitFormat.CELSIUS

    private val _weatherRes = MutableLiveData<WeatherRes>()
    val weatherRes: LiveData<WeatherRes> get() = _weatherRes

    private val _showMessage = MutableLiveData<Int>()
    val showMessage: LiveData<Int> get() = _showMessage

    fun setUnitFormat(unitFormat: UnitFormat) {
        this.unitFormat = unitFormat
    }

    fun getWeatherByCity(city: String) {
        viewModelScope.launch {
            val res = weatherRepository.getWeatherByCity(city, unitFormat)
            if (res.isSuccessful) {
                _weatherRes.value = res.body()
            } else {
                Log.d("DEV", "is not success")
                _showMessage.value = R.string.error_message_city_not_found
            }
        }
    }
}