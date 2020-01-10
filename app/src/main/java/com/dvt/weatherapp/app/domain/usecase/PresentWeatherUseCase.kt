package com.dvt.weatherapp.app.domain.usecase

import androidx.lifecycle.LiveData
import com.dvt.weatherapp.app.domain.repo.entity.PresentWeatherEntity
import com.dvt.weatherapp.app.domain.repo.PresentWeatherRepository
import com.dvt.weatherapp.app.utils.UseCaseLiveData
import com.dvt.weatherapp.app.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-11
 */

class PresentWeatherUseCase @Inject internal constructor(private val repository: PresentWeatherRepository) : UseCaseLiveData<Resource<PresentWeatherEntity>, PresentWeatherUseCase.PresentWeatherParams, PresentWeatherRepository>() {

    override fun getRepository(): PresentWeatherRepository {
        return repository
    }

    override fun buildUseCaseObservable(params: PresentWeatherParams?): LiveData<Resource<PresentWeatherEntity>> {
        return repository.loadPresentWeatherByGeoCords(
            params?.lat?.toDouble() ?: 0.0,
            params?.lon?.toDouble() ?: 0.0,
            params?.fetchRequired
                ?: false,
            units = params?.units ?: "metric"
        )
    }

    class PresentWeatherParams(
        val lat: String = "",
        val lon: String = "",
        val fetchRequired: Boolean,
        val units: String
    ) : Params()
}
