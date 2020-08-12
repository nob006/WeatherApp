package com.violet.openweather.modul

import com.violet.openweather.network.RetrofitClient
import com.violet.openweather.network.WeatherService
import com.violet.openweather.repositoty.WeatherRepository
import com.violet.openweather.viewmodel.WeatherViewModel
import com.violet.openweather.viewmodel.WholeDayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoModules = module {
    single { WeatherRepository(get()) }
}

val serviceModules = module {
    single { RetrofitClient().create(WeatherService::class.java) }
}

val viewModelModules = module {
    viewModel { WeatherViewModel(get()) }
    viewModel { WholeDayViewModel(get()) }
}