package com.dvt.weatherapp.app

import android.app.Activity
import android.app.Application
import android.os.Build
import com.facebook.stetho.Stetho
import com.dvt.weatherapp.app.di.AppInjector
import com.jakewharton.threetenabp.AndroidThreeTen 
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import timber.log.Timber

class DvtWeatherApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG && !isRoboUnitTest()) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }

        AppInjector.init(this)
        AndroidThreeTen.init(this)
    }

    private fun isRoboUnitTest(): Boolean {
        return "robolectric" == Build.FINGERPRINT
    }
}
