package com.dvt.weatherapp.app.domain.repo.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.dvt.weatherapp.app.domain.model.Wind
import kotlinx.android.parcel.Parcelize

/**
 * Created by Masi on 2019-12-13
 */

@Parcelize
@Entity(tableName = "Wind")
data class WindEntity(
    @ColumnInfo(name = "deg")
    val deg: Double?,
    @ColumnInfo(name = "speed")
    val speed: Double?
) : Parcelable {
    constructor(wind: Wind?) : this(
        deg = wind?.deg,
        speed = wind?.speed
    )
}
