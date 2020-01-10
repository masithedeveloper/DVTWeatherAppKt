package com.dvt.weatherapp.app.ui.splash

import android.content.SharedPreferences
import com.dvt.weatherapp.app.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-10
 */

class SplashFragmentViewModel @Inject constructor(var sharedPreferences: SharedPreferences) : BaseViewModel() {
    var navigateDashboard = false
}
