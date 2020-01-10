package com.dvt.weatherapp.app.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dvt.weatherapp.app.di.ViewModelFactory
import com.dvt.weatherapp.app.di.key.ViewModelKey
import com.dvt.weatherapp.app.ui.dashboard.DashboardFragmentViewModel
import com.dvt.weatherapp.app.ui.dashboard.predictable.PredictableItemViewModel
import com.dvt.weatherapp.app.ui.main.MainActivityViewModel
import com.dvt.weatherapp.app.ui.search.SearchViewModel
import com.dvt.weatherapp.app.ui.search.result.SearchResultItemViewModel
import com.dvt.weatherapp.app.ui.splash.SplashFragmentViewModel
import com.dvt.weatherapp.app.ui.weather_detail.WeatherDetailViewModel
import com.dvt.weatherapp.app.ui.weather_detail.weatherHourOfDay.WeatherHourOfDayItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Masi on 2019-12-10
 */

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(SplashFragmentViewModel::class)
    abstract fun provideSplashFragmentViewModel(splashFragmentViewModel: SplashFragmentViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun provideMainViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(DashboardFragmentViewModel::class)
    abstract fun provideDashboardFragmentViewModel(dashboardFragmentViewModel: DashboardFragmentViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(PredictableItemViewModel::class)
    abstract fun providePredictableItemViewModel(predictableItemViewModel: PredictableItemViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(WeatherDetailViewModel::class)
    abstract fun provideWeatherDetailViewModel(weatherDetailViewModel: WeatherDetailViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(WeatherHourOfDayItemViewModel::class)
    abstract fun provideWeatherHourOfDayItemViewModel(weatherHourOfDayItemViewModel: WeatherHourOfDayItemViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SearchViewModel::class)
    abstract fun provideSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SearchResultItemViewModel::class)
    abstract fun provideSearchResultItemViewModel(searchResultItemViewModel: SearchResultItemViewModel): ViewModel
}
