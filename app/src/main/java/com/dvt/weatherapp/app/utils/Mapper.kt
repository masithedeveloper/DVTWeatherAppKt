package com.dvt.weatherapp.app.utils

/**
 * Created by Masi on 2019-12-1122019-12-12
 */

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}
