package com.dvt.weatherapp.app.domain.repo.entity

import android.graphics.Color
import android.os.Parcelable
import androidx.room.*
import com.dvt.weatherapp.app.domain.model.PresentWeatherResponse
import com.dvt.weatherapp.app.domain.model.WeatherItem
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate

/**
 * Created by Masi on 2019-12-14
 */

@Parcelize
@Entity(tableName = "PresentWeather")
data class PresentWeatherEntity(
        @ColumnInfo(name = "visibility")
    var visibility: Int?,
        @ColumnInfo(name = "timezone")
    var timezone: Int?,
        @Embedded
    var main: MainEntity?,
        @Embedded
    var clouds: CloudsEntity?,
        @ColumnInfo(name = "dt")
    var dt: Long?,
        @ColumnInfo(name = "weather")
    val weather: List<WeatherItem?>? = null,
        @ColumnInfo(name = "name")
    val name: String?,
        @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
        @ColumnInfo(name = "base")
    val base: String?,
        @Embedded
    val wind: WindEntity?
) : Parcelable {
    @Ignore
    constructor(presentWeather: PresentWeatherResponse) : this(
        visibility = presentWeather.visibility,
        timezone = presentWeather.timezone,
        main = MainEntity(presentWeather.main),
        clouds = CloudsEntity(presentWeather.clouds),
        dt = presentWeather.dt?.toLong(),
        weather = presentWeather.weather,
        name = presentWeather.name,
        id = 0,
        base = presentWeather.base,
        wind = WindEntity(presentWeather.wind)
    )

    fun getPresentWeather(): WeatherItem? {
        return weather?.first()
    }

    private fun getDateTime(s: Long): DayOfWeek? {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(s * 1000)
            val formattedDate = sdf.format(netDate)

            LocalDate.of(
                formattedDate.substringAfterLast("/").toInt(),
                formattedDate.substringAfter("/").take(2).toInt(),
                formattedDate.substringBefore("/").toInt()
            )
                .dayOfWeek
        } catch (e: Exception) {
            e.printStackTrace()
            DayOfWeek.MONDAY
        }
    }

    fun getColor(): Int {
        return when (dt?.let { getDateTime(it) }) {
            DayOfWeek.MONDAY -> Color.parseColor("#28E0AE")
            DayOfWeek.TUESDAY -> Color.parseColor("#FF0090")
            DayOfWeek.WEDNESDAY -> Color.parseColor("#FFAE00")
            DayOfWeek.THURSDAY -> Color.parseColor("#0090FF")
            DayOfWeek.FRIDAY -> Color.parseColor("#DC0000")
            DayOfWeek.SATURDAY -> Color.parseColor("#0051FF")
            DayOfWeek.SUNDAY -> Color.parseColor("#3D28E0")
            else -> Color.parseColor("#28E0AE")
        }
    }
}
