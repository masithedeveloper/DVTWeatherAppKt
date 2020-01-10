package com.dvt.weatherapp.app.domain.datasource.predictable

import com.dvt.weatherapp.app.domain.repo.dao.PredictableDao
import com.dvt.weatherapp.app.domain.repo.entity.PredictableEntity
import com.dvt.weatherapp.app.domain.model.PredictableResponse
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-11
 */

class PredictableLocalDataSource @Inject constructor(private val predictableDao: PredictableDao) {

    fun getPredictable() = predictableDao.getPredictable()

    fun insertPredictable(predictable: PredictableResponse) = predictableDao.deleteAndInsert(PredictableEntity(predictable))
}
