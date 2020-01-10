package com.dvt.weatherapp.app.domain.datasource.presentWeather

import com.dvt.weatherapp.app.domain.WeatherAppAPI
import com.dvt.weatherapp.app.domain.model.PresentWeatherResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-13
 */

class PresentWeatherRemoteDataSource @Inject constructor(private val api: WeatherAppAPI) {

    fun getPresentWeatherByGeoCords(lat: Double, lon: Double, units: String): Single<PresentWeatherResponse> = api.getPresentByGeoCords(lat, lon, units)
}
