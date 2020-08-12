package com.violet.openweather.fragment.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChangeEvents
import com.violet.openweather.R
import com.violet.openweather.databinding.FragmentWeatherBinding
import com.violet.openweather.extension.clearInputText
import com.violet.openweather.extension.hideKeyboard
import com.violet.openweather.fragment.weather.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class WeatherFragment : Fragment() {

    companion object {
        private const val TIME_DELAY = 1500L
    }

    private val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = DataBindingUtil.inflate<FragmentWeatherBinding>(
                inflater,
                R.layout.fragment_weather,
                container,
                false
        )
        view.viewModel = weatherViewModel
        view.lifecycleOwner = this
        return view.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        handleListener()
        initObserve()
    }

    private fun handleListener() {
        edtSearch.textChangeEvents()
                .debounce(TIME_DELAY, TimeUnit.MILLISECONDS)
                .filter {
                    it.text.isNotEmpty()
                }
                .subscribe {
                    hideKeyboard()
                    val search = edtSearch.text.toString().trim()
                    edtSearch.clearInputText()
                    if (search.isNotEmpty()) {
                        weatherViewModel.getWeatherByCity(search)
                    } else {
                        showMessage(R.string.error_message_city_validate)
                    }
                }

        btnMoreDetail.clicks()
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribe {
                    weatherViewModel.weatherRes.value?.coord?.let {
                        findNavController().navigate(WeatherFragmentDirections.actionWeatherFragmentToWholedayFragment(it.lat.toString(), it.lon.toString()))
                    }
                }
    }

    private fun initObserve() {
        weatherViewModel.showMessage.observe(viewLifecycleOwner, Observer { messageId ->
            showMessage(messageId)
        })
    }

    private fun showMessage(messageId: Int) {
        Snackbar.make(edtSearch, getText(messageId), Snackbar.LENGTH_SHORT).show()
    }
}
