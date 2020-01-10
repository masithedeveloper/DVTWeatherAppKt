package com.dvt.weatherapp.app.domain.datasource.predictable

import com.dvt.weatherapp.app.domain.WeatherAppAPI
import com.dvt.weatherapp.app.domain.model.PredictableResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-11
 */

class PredictableRemoteDataSource @Inject constructor(private val api: WeatherAppAPI) {

    fun getPredictableByGeoCords(lat: Double, lon: Double, units: String): Single<PredictableResponse> = api.getPredictableByGeoCords(lat, lon, units)
}
