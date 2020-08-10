package com.violet.openweather

import android.app.Application
import com.violet.openweather.modul.repoModules
import com.violet.openweather.modul.serviceModules
import com.violet.openweather.modul.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(repoModules, serviceModules, viewModelModules))
        }
    }
}