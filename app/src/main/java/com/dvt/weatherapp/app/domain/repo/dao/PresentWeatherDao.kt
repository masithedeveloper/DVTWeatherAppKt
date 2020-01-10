package com.dvt.weatherapp.app.domain.repo.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dvt.weatherapp.app.domain.repo.entity.PresentWeatherEntity

/**
 * Created by Masi on 2019-12-10
 */

@Dao
interface PresentWeatherDao {

    @Query("SELECT * FROM PresentWeather")
    fun getPresentWeather(): LiveData<PresentWeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPresentWeather(presentWeatherEntity: PresentWeatherEntity)

    @Transaction
    fun deleteAndInsert(presentWeatherEntity: PresentWeatherEntity) {
        deletePresentWeather()
        insertPresentWeather(presentWeatherEntity)
    }

    @Query("DELETE FROM PresentWeather")
    fun deletePresentWeather()

    @Query("Select count(*) from PresentWeather")
    fun getCount(): Int
}
