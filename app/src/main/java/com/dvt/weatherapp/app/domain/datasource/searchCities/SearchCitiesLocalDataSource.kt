package com.dvt.weatherapp.app.domain.datasource.searchCities

import androidx.lifecycle.LiveData
import com.dvt.weatherapp.app.domain.repo.dao.CitiesForSearchDao
import com.dvt.weatherapp.app.domain.repo.entity.CitiesForSearchEntity
import com.dvt.weatherapp.app.domain.model.SearchResponse
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-11
 */

class SearchCitiesLocalDataSource @Inject constructor(private val citiesForSearchDao: CitiesForSearchDao) {

    fun getCityByName(cityName: String?): LiveData<List<CitiesForSearchEntity>> = citiesForSearchDao.getCityByName(cityName)

    fun insertCities(response: SearchResponse) {
        response.hits?.forEach {
            citiesForSearchDao.insertCity(CitiesForSearchEntity(it))
        }
    }
}
