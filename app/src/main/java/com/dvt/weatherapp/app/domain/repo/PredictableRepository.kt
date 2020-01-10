package com.dvt.weatherapp.app.domain.repo

import NetworkBoundResource
import androidx.lifecycle.LiveData
import com.dvt.weatherapp.app.domain.repo.entity.PredictableEntity
import com.dvt.weatherapp.app.domain.datasource.predictable.PredictableLocalDataSource
import com.dvt.weatherapp.app.domain.datasource.predictable.PredictableRemoteDataSource
import com.dvt.weatherapp.app.domain.model.PredictableResponse
import com.dvt.weatherapp.app.utils.domain.RateLimiter
import com.dvt.weatherapp.app.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-11
 */

class PredictableRepository @Inject constructor(private val predictableRemoteDataSource: PredictableRemoteDataSource, private val predictableLocalDataSource: PredictableLocalDataSource) {

    private val predictableListRateLimit = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun loadPredictableByCoord(lat: Double, lon: Double, fetchRequired: Boolean, units: String): LiveData<Resource<PredictableEntity>> {
        return object : NetworkBoundResource<PredictableEntity, PredictableResponse>() {
            override fun saveCallResult(item: PredictableResponse) = predictableLocalDataSource.insertPredictable(item)

            override fun shouldFetch(data: PredictableEntity?): Boolean = fetchRequired

            override fun loadFromDb(): LiveData<PredictableEntity> = predictableLocalDataSource.getPredictable()

            override fun createCall(): Single<PredictableResponse> = predictableRemoteDataSource.getPredictableByGeoCords(lat, lon, units)

            override fun onFetchFailed() = predictableListRateLimit.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }

    companion object {

        private const val RATE_LIMITER_TYPE = "data"
    }
}
