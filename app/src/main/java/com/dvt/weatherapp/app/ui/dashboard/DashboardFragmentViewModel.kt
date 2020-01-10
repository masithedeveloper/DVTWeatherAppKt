package com.dvt.weatherapp.app.ui.dashboard

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dvt.weatherapp.app.base.BaseViewModel
import com.dvt.weatherapp.app.domain.repo.entity.PresentWeatherEntity
import com.dvt.weatherapp.app.domain.repo.entity.PredictableEntity
import com.dvt.weatherapp.app.domain.usecase.PresentWeatherUseCase
import com.dvt.weatherapp.app.domain.usecase.PredictableUseCase
import com.dvt.weatherapp.app.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-12
 */

class DashboardFragmentViewModel @Inject internal constructor(private val predictableUseCase: PredictableUseCase, private val presentWeatherUseCase: PresentWeatherUseCase, var sharedPreferences: SharedPreferences) : BaseViewModel() {

    var predictableParams: MutableLiveData<PredictableUseCase.PredictableParams> = MutableLiveData()
    var presentWeatherParams: MutableLiveData<PresentWeatherUseCase.PresentWeatherParams> = MutableLiveData()
    private val predictableLiveData: LiveData<Resource<PredictableEntity>> = Transformations.switchMap(
        predictableParams
    ) {
        return@switchMap predictableUseCase.execute(it)
    }
    private val presentWeatherLiveData: LiveData<Resource<PresentWeatherEntity>> = Transformations.switchMap(
        presentWeatherParams
    ) {
        return@switchMap presentWeatherUseCase.execute(it)
    }

    private val predictableViewState: LiveData<PredictableViewState> = Transformations.map(predictableLiveData) {
        return@map onPredictableResultReady(it)
    }

    private val presentWeatherViewState: LiveData<PresentWeatherViewState> = Transformations.map(presentWeatherLiveData) {
        return@map onPresentWeatherResultReady(it)
    }

    fun getPredictableViewState() = predictableViewState
    fun getPresentWeatherViewState() = presentWeatherViewState

    private fun onPredictableResultReady(resource: Resource<PredictableEntity>): PredictableViewState {
        return PredictableViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    private fun onPresentWeatherResultReady(resource: Resource<PresentWeatherEntity>): PresentWeatherViewState {
        return PresentWeatherViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }
}
