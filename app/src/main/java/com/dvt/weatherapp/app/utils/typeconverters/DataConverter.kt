package com.dvt.weatherapp.app.utils.typeconverters

import androidx.room.TypeConverter
import com.dvt.weatherapp.app.domain.model.ListItem
import com.dvt.weatherapp.app.domain.model.WeatherItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Created by Masi on 2019-12-11
 */

object DataConverter {

    @TypeConverter
    @JvmStatic
    fun stringToList(data: String?): List<ListItem>? {
        if (data == null) {
            return emptyList()
        }

        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, ListItem::class.java)
        val adapter = moshi.adapter<List<ListItem>>(type)
        return adapter.fromJson(data)
    }

    @TypeConverter
    @JvmStatic
    fun listToString(objects: List<ListItem>): String {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, ListItem::class.java)
        val adapter = moshi.adapter<List<ListItem>>(type)
        return adapter.toJson(objects)
    }

    @TypeConverter
    @JvmStatic
    fun weatherStringToList(data: String?): List<WeatherItem>? {
        if (data == null) {
            return emptyList()
        }

        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, WeatherItem::class.java)
        val adapter = moshi.adapter<List<WeatherItem>>(type)
        return adapter.fromJson(data)
    }

    @TypeConverter
    @JvmStatic
    fun weatherListToString(objects: List<WeatherItem>): String {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, WeatherItem::class.java)
        val adapter = moshi.adapter<List<WeatherItem>>(type)
        return adapter.toJson(objects)
    }
}
