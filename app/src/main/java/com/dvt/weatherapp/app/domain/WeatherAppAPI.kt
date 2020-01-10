package com.dvt.weatherapp.app.domain

import com.dvt.weatherapp.app.domain.model.PresentWeatherResponse
import com.dvt.weatherapp.app.domain.model.PredictableResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Masi on 2019-12-10
 */

interface WeatherAppAPI {

    @GET("forecast")
    fun getPredictableByGeoCords(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("units")
        units: String
    ): Single<PredictableResponse>

    @GET("weather")
    fun getPresentByGeoCords(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("units")
        units: String
    ): Single<PresentWeatherResponse>
}
