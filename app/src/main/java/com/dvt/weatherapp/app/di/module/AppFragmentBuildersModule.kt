package com.dvt.weatherapp.app.di.module

import com.dvt.weatherapp.app.ui.dashboard.DashboardFragment
import com.dvt.weatherapp.app.ui.search.SearchFragment
import com.dvt.weatherapp.app.ui.splash.SplashFragment
import com.dvt.weatherapp.app.ui.weather_detail.WeatherDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Masi on 2019-12-11
 */

@Module
abstract class AppFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun contributeWeatherDetailFragment(): WeatherDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment
}
