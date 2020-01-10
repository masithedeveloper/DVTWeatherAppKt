package com.dvt.weatherapp.app.domain.repo

import NetworkBoundResource
import androidx.lifecycle.LiveData
import com.dvt.weatherapp.app.domain.repo.entity.PresentWeatherEntity
import com.dvt.weatherapp.app.domain.datasource.presentWeather.PresentWeatherLocalDataSource
import com.dvt.weatherapp.app.domain.datasource.presentWeather.PresentWeatherRemoteDataSource
import com.dvt.weatherapp.app.domain.model.PresentWeatherResponse
import com.dvt.weatherapp.app.utils.domain.RateLimiter
import com.dvt.weatherapp.app.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-13
 */

class PresentWeatherRepository @Inject constructor(private val presentWeatherRemoteDataSource: PresentWeatherRemoteDataSource, private val presentWeatherLocalDataSource: PresentWeatherLocalDataSource) {

    private val presentWeatherRateLimit = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun loadPresentWeatherByGeoCords(lat: Double, lon: Double, fetchRequired: Boolean, units: String): LiveData<Resource<PresentWeatherEntity>> {
        return object : NetworkBoundResource<PresentWeatherEntity, PresentWeatherResponse>() {
            override fun saveCallResult(item: PresentWeatherResponse) = presentWeatherLocalDataSource.insertPresentWeather(item)

            override fun shouldFetch(data: PresentWeatherEntity?): Boolean = fetchRequired

            override fun loadFromDb(): LiveData<PresentWeatherEntity> = presentWeatherLocalDataSource.getPresentWeather()

            override fun createCall(): Single<PresentWeatherResponse> = presentWeatherRemoteDataSource.getPresentWeatherByGeoCords(lat, lon, units)

            override fun onFetchFailed() = presentWeatherRateLimit.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }

    companion object {

        private const val RATE_LIMITER_TYPE = "data"
    }
}
