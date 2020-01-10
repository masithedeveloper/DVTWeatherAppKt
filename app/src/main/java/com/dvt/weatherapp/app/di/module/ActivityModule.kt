package com.dvt.weatherapp.app.di.module

import com.dvt.weatherapp.app.di.scope.PerActivity
import com.dvt.weatherapp.app.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Masi on 2019-12-10
 */

@Module
abstract class ActivityModule {

    /**
     * Injects [MainActivity]
     *
     * @return an instance of [MainActivity]
     */

    @PerActivity
    @ContributesAndroidInjector(modules = [AppFragmentBuildersModule::class])
    internal abstract fun mainActivity(): MainActivity
}
