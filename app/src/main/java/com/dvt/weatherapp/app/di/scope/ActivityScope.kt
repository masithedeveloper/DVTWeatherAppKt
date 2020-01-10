package com.dvt.weatherapp.app.di.scope

import javax.inject.Scope
import kotlin.annotation.Retention

/**
 * Created by Masi on 2019-12-11
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity
