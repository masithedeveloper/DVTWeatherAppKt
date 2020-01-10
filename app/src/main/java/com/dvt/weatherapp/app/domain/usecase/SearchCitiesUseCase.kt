package com.dvt.weatherapp.app.domain.usecase

import androidx.lifecycle.LiveData
import com.dvt.weatherapp.app.domain.repo.entity.CitiesForSearchEntity
import com.dvt.weatherapp.app.domain.repo.SearchCitiesRepository
import com.dvt.weatherapp.app.utils.UseCaseLiveData
import com.dvt.weatherapp.app.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-11
 */

class SearchCitiesUseCase @Inject internal constructor(private val repository: SearchCitiesRepository) : UseCaseLiveData<Resource<List<CitiesForSearchEntity>>, SearchCitiesUseCase.SearchCitiesParams, SearchCitiesRepository>() {
    override fun getRepository(): SearchCitiesRepository = repository

    override fun buildUseCaseObservable(params: SearchCitiesParams?): LiveData<Resource<List<CitiesForSearchEntity>>> {
        return repository.loadCitiesByCityName(
            cityName = params?.city ?: ""
        )
    }

    class SearchCitiesParams(
        val city: String = ""
    ) : Params()
}
