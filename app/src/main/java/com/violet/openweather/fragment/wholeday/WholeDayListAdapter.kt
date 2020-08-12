package com.violet.openweather.fragment.wholeday

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.violet.openweather.databinding.LayoutWholeDayBinding
import com.violet.openweather.model.wholeday.Hourly

class WholeDayListAdapter(private val list: List<Hourly>) : RecyclerView.Adapter<WholeDayListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = LayoutWholeDayBinding.inflate(inflater, parent, false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(private val view: LayoutWholeDayBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(hourly: Hourly) {
            view.hourly = hourly
            view.executePendingBindings()
        }
    }
}