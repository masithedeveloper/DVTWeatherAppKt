package com.dvt.weatherapp.app.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Masi on 2019-12-10
 */

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)
}
