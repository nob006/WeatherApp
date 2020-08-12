package com.violet.openweather.fragment.wholeday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.violet.openweather.R
import com.violet.openweather.model.wholeday.WholeDayRes
import com.violet.openweather.repositoty.WeatherRepository
import kotlinx.coroutines.launch

class WholeDayViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _wholeDayRes = MutableLiveData<WholeDayRes>()
    val wholeDayRes: LiveData<WholeDayRes> get() = _wholeDayRes

    private val _showMessage = MutableLiveData<Int>()
    val showMessage: LiveData<Int> get() = _showMessage

    fun getWeatherWholeDay(lat: String, lon: String) {
        viewModelScope.launch {
            val res = weatherRepository.getWeatherWholeDay(lat, lon)
            if (res.isSuccessful) {
                _wholeDayRes.value = res.body()
            } else {
                _showMessage.value = R.string.something_wrong
            }
        }
    }
}