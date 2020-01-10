package com.dvt.weatherapp.app.domain.repo.entity

import android.os.Parcelable
import androidx.room.*
import com.dvt.weatherapp.app.domain.model.PredictableResponse
import com.dvt.weatherapp.app.domain.model.ListItem
import kotlinx.android.parcel.Parcelize

/**
 * Created by Masi on 2019-12-11
 */

@Parcelize
@Entity(tableName = "Predictable")
data class PredictableEntity(

        @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

        @Embedded
    var city: CityEntity?,

        @ColumnInfo(name = "list")
    var list: List<ListItem>?
) : Parcelable {

    @Ignore
    constructor(predictableResponse: PredictableResponse) : this(
        id = 0,
        city = predictableResponse.city?.let { CityEntity(it) },
        list = predictableResponse.list
    )
}
