package com.violet.openweather.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.violet.openweather.R
import com.violet.openweather.viewmodel.WeatherViewModel
import com.violet.openweather.viewmodel.WholeDayViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondFragment : Fragment() {

    private val wholeDayViewModel: WholeDayViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        wholeDayViewModel.getWeatherWholeDay()
    }
}