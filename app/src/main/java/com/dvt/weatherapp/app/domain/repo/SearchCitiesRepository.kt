package com.dvt.weatherapp.app.domain.repo

import NetworkBoundResource
import androidx.lifecycle.LiveData
import com.dvt.weatherapp.app.domain.repo.entity.CitiesForSearchEntity
import com.dvt.weatherapp.app.domain.datasource.searchCities.SearchCitiesLocalDataSource
import com.dvt.weatherapp.app.domain.datasource.searchCities.SearchCitiesRemoteDataSource
import com.dvt.weatherapp.app.domain.model.SearchResponse
import com.dvt.weatherapp.app.utils.domain.RateLimiter
import com.dvt.weatherapp.app.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-11
 */

class SearchCitiesRepository @Inject constructor(private val searchCitiesLocalDataSource: SearchCitiesLocalDataSource, private val searchCitiesRemoteDataSource: SearchCitiesRemoteDataSource) {

    private val rateLimiter = RateLimiter<String>(1, TimeUnit.SECONDS)

    fun loadCitiesByCityName(cityName: String?): LiveData<Resource<List<CitiesForSearchEntity>>> {
        return object : NetworkBoundResource<List<CitiesForSearchEntity>, SearchResponse>() {
            override fun saveCallResult(item: SearchResponse) = searchCitiesLocalDataSource.insertCities(item)

            override fun shouldFetch(data: List<CitiesForSearchEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<CitiesForSearchEntity>> = searchCitiesLocalDataSource.getCityByName(cityName)

            override fun createCall(): Single<SearchResponse> = searchCitiesRemoteDataSource.getCityWithQuery(
                cityName
                    ?: ""
            )

            override fun onFetchFailed() = rateLimiter.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }

    companion object {
        private const val RATE_LIMITER_TYPE = "data"
    }
}
