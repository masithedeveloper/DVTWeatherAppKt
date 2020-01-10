package com.dvt.weatherapp.app.domain.datasource.presentWeather

import com.dvt.weatherapp.app.domain.repo.dao.PresentWeatherDao
import com.dvt.weatherapp.app.domain.repo.entity.PresentWeatherEntity
import com.dvt.weatherapp.app.domain.model.PresentWeatherResponse
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-13
 */

class PresentWeatherLocalDataSource @Inject constructor(private val presentWeatherDao: PresentWeatherDao) {

    fun getPresentWeather() = presentWeatherDao.getPresentWeather()

    fun insertPresentWeather(presentWeather: PresentWeatherResponse) = presentWeatherDao.deleteAndInsert(PresentWeatherEntity(presentWeather))
}
