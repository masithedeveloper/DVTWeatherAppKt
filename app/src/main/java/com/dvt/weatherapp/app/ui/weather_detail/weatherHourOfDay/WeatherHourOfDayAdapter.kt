package com.dvt.weatherapp.app.ui.weather_detail.weatherHourOfDay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.dvt.weatherapp.app.R
import com.dvt.weatherapp.app.adapter.BaseAdapter
import com.dvt.weatherapp.app.databinding.ItemWeatherHourOfDayBinding
import com.dvt.weatherapp.app.domain.model.ListItem

/**
 * Created by Masi on 2019-12-11
 */

class WeatherHourOfDayAdapter(private val callBack: (ListItem) -> Unit) : BaseAdapter<ListItem>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = DataBindingUtil.inflate<ItemWeatherHourOfDayBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_weather_hour_of_day,
            parent,
            false
        )
        val viewModel = WeatherHourOfDayItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callBack.invoke(it)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemWeatherHourOfDayBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<ListItem>() {
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.dtTxt == newItem.dtTxt
}
