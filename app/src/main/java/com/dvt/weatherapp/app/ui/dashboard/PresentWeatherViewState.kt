package com.dvt.weatherapp.app.ui.dashboard

import com.dvt.weatherapp.app.domain.repo.entity.PresentWeatherEntity
import com.dvt.weatherapp.app.utils.domain.Status

/**
 * Created by Masi on 2019-12-13
 */

class PresentWeatherViewState(
    val status: Status,
    val error: String? = null,
    val data: PresentWeatherEntity? = null
) {
    fun getPresent() = data

    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error

    fun shouldShowErrorMessage() = error != null
}
