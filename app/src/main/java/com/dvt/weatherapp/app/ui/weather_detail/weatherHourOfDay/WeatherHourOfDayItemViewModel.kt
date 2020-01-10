package com.dvt.weatherapp.app.ui.weather_detail.weatherHourOfDay

import androidx.databinding.ObservableField
import com.dvt.weatherapp.app.base.BaseViewModel
import com.dvt.weatherapp.app.domain.model.ListItem
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-11
 */

class WeatherHourOfDayItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<ListItem>()
}
