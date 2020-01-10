package com.dvt.weatherapp.app.ui.dashboard

import com.dvt.weatherapp.app.domain.repo.entity.PredictableEntity
import com.dvt.weatherapp.app.utils.domain.Status

/**
 * Created by Masi on 2019-12-11
 */

class PredictableViewState(
    val status: Status,
    val error: String? = null,
    val data: PredictableEntity? = null
) {
    fun getPredictable() = data

    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error

    fun shouldShowErrorMessage() = error != null
}
