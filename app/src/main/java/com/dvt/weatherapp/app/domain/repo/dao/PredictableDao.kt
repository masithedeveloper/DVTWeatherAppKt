package com.dvt.weatherapp.app.domain.repo.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.dvt.weatherapp.app.domain.repo.entity.PredictableEntity

@Dao
interface PredictableDao {

    @Query("SELECT * FROM Predictable")
    fun getPredictable(): LiveData<PredictableEntity>

    @Query("SELECT * FROM Predictable ORDER BY abs(lat-:lat) AND abs(lon-:lon) LIMIT 1")
    fun getPredictableByCoord(lat: Double, lon: Double): LiveData<PredictableEntity>

    @Insert(onConflict = REPLACE)
    fun insertPredictable(predictable: PredictableEntity)

    @Transaction
    fun deleteAndInsert(predictable: PredictableEntity) {
        deleteAll()
        insertPredictable(predictable)
    }

    @Update
    fun updatePredictable(predictable: PredictableEntity)

    @Delete
    fun deletePredictable(predictable: PredictableEntity)

    @Query("DELETE FROM Predictable")
    fun deleteAll()

    @Query("Select count(*) from Predictable")
    fun getCount(): Int
}
