package com.violet.openweather.fragment.wholeday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.violet.openweather.R
import com.violet.openweather.fragment.wholeday.viewmodel.WholeDayViewModel
import com.violet.openweather.model.wholeday.Hourly
import kotlinx.android.synthetic.main.fragment_whole_day.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WholeDayFragment : Fragment() {

    private val wholeDayViewModel: WholeDayViewModel by viewModel()

    companion object {
        const val KEY_LAT = "lat"
        const val KEY_LON = "lon"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_whole_day, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initObserve()

        val lat = arguments?.getString(KEY_LAT) ?: ""
        val lon = arguments?.getString(KEY_LON) ?: ""
        wholeDayViewModel.getWeatherWholeDay(lat, lon)
    }

    private fun initObserve() {
        wholeDayViewModel.wholeDayRes.observe(viewLifecycleOwner, Observer {
            initView(it.hourly)
        })

        wholeDayViewModel.showMessage.observe(viewLifecycleOwner, Observer {
            showMessage(it)
        })
    }

    private fun initView(list: List<Hourly>) {
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = WholeDayListAdapter(list)
    }

    private fun showMessage(messageId: Int) {
        Snackbar.make(recycleView, getText(messageId), Snackbar.LENGTH_SHORT).show()
    }
}