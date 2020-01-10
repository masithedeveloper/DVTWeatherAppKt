package com.dvt.weatherapp.app.domain.repo.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.dvt.weatherapp.app.domain.model.Clouds
import kotlinx.android.parcel.Parcelize

/**
 * Created by Masi on 2019-12-13
 */

@Parcelize
@Entity(tableName = "Clouds")
data class CloudsEntity(
    @ColumnInfo(name = "all")
    var all: Int
) : Parcelable {
    @Ignore
    constructor(clouds: Clouds?) : this(
        all = clouds?.all ?: 0
    )
}
