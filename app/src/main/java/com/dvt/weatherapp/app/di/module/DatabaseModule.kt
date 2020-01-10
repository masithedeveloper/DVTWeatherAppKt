package com.dvt.weatherapp.app.di.module

import android.content.Context
import androidx.room.Room
import com.dvt.weatherapp.app.domain.repo.WeatherDatabase
import com.dvt.weatherapp.app.domain.repo.dao.CitiesForSearchDao
import com.dvt.weatherapp.app.domain.repo.dao.PresentWeatherDao
import com.dvt.weatherapp.app.domain.repo.dao.PredictableDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Masi on 2019-12-12
 */

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java, "WeatherApp-DB"
        ).build()
    }

    @Singleton
    @Provides
    fun providePredictableDao(db: WeatherDatabase): PredictableDao {
        return db.predictableDao()
    }

    @Singleton
    @Provides
    fun providePresentWeatherDao(db: WeatherDatabase): PresentWeatherDao {
        return db.presentWeatherDao()
    }

    @Singleton
    @Provides
    fun provideCitiesForSearchDao(db: WeatherDatabase): CitiesForSearchDao {
        return db.citiesForSearchDao()
    }
}
