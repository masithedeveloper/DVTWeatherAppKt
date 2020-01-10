package com.dvt.weatherapp.app.ui.weather_detail

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dvt.weatherapp.app.base.BaseViewModel
import com.dvt.weatherapp.app.domain.repo.entity.PredictableEntity
import com.dvt.weatherapp.app.domain.datasource.predictable.PredictableLocalDataSource
import com.dvt.weatherapp.app.domain.model.ListItem
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-11
 */

class WeatherDetailViewModel @Inject constructor(private val predictableLocalDataSource: PredictableLocalDataSource) : BaseViewModel() {

    var weatherItem: ObservableField<ListItem> = ObservableField()
    private var predictableLiveData: LiveData<PredictableEntity> = MutableLiveData()
    var selectedDayDate: String? = null
    var selectedDayPredictableLiveData: MutableLiveData<List<ListItem>> = MutableLiveData()
    fun getPredictableLiveData() = predictableLiveData

    init {
        getPredictable()
    }

    private fun getPredictable(): LiveData<PredictableEntity> {
        predictableLiveData =
            Transformations.switchMap(
                predictableLocalDataSource.getPredictable()
            ) {
                val predictableLiveData = MutableLiveData<PredictableEntity>()
                predictableLiveData.value = it
                return@switchMap predictableLiveData
            }

        return predictableLiveData
    }
}
