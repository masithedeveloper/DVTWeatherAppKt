package com.dvt.weatherapp.app.domain.repo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dvt.weatherapp.app.domain.repo.entity.CitiesForSearchEntity

/**
 * Created by Masi on 2019-12-11
 */

@Dao
interface CitiesForSearchDao {

    @Query("SELECT * FROM CitiesForSearch")
    fun getCities(): LiveData<List<CitiesForSearchEntity>>

    @Query("SELECT * FROM CitiesForSearch WHERE fullName like '%' || :city || '%'|| '%' ORDER BY fullName DESC")
    fun getCityByName(city: String? = ""): LiveData<List<CitiesForSearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(citiesForSearchEntity: CitiesForSearchEntity)

    @Query("DELETE FROM CitiesForSearch")
    fun deleteCities()

    @Query("Select count(*) from CitiesForSearch")
    fun getCount(): Int
}
