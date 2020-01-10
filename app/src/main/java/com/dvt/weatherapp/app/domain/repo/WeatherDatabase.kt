package com.dvt.weatherapp.app.domain.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dvt.weatherapp.app.domain.repo.dao.CitiesForSearchDao
import com.dvt.weatherapp.app.domain.repo.dao.PresentWeatherDao
import com.dvt.weatherapp.app.domain.repo.dao.PredictableDao
import com.dvt.weatherapp.app.domain.repo.entity.CitiesForSearchEntity
import com.dvt.weatherapp.app.domain.repo.entity.PresentWeatherEntity
import com.dvt.weatherapp.app.domain.repo.entity.PredictableEntity
import com.dvt.weatherapp.app.utils.typeconverters.DataConverter

@Database(
    entities = [
        PredictableEntity::class,
        PresentWeatherEntity::class,
        CitiesForSearchEntity::class
    ],
    version = 2
)
@TypeConverters(DataConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun predictableDao(): PredictableDao

    abstract fun presentWeatherDao(): PresentWeatherDao

    abstract fun citiesForSearchDao(): CitiesForSearchDao
}
