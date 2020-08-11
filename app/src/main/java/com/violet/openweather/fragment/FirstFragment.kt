package com.violet.openweather.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChangeEvents
import com.violet.openweather.R
import com.violet.openweather.databinding.FragmentFirstBinding
import com.violet.openweather.extension.clearInputText
import com.violet.openweather.extension.hideKeyboard
import com.violet.openweather.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_first.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class FirstFragment : Fragment() {

    companion object {
        private const val TIME_DELAY = 1500L
    }

    private val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = DataBindingUtil.inflate<FragmentFirstBinding>(
            inflater,
            R.layout.fragment_first,
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
    }

    private fun initObserve() {
        weatherViewModel.showMessage.observe(viewLifecycleOwner, Observer { messageId ->
            showMessage(messageId)
        })
    }

    private fun showMessage(messageId: Int) {
        Snackbar.make(edtSearch, getText(messageId), Snackbar.LENGTH_SHORT)
            .show()
    }
}
