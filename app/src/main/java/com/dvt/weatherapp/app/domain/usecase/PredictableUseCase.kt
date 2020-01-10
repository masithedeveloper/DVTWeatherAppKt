package com.dvt.weatherapp.app.domain.usecase

import androidx.lifecycle.LiveData
import com.dvt.weatherapp.app.domain.repo.entity.PredictableEntity
import com.dvt.weatherapp.app.domain.repo.PredictableRepository
import com.dvt.weatherapp.app.utils.UseCaseLiveData
import com.dvt.weatherapp.app.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-11
 */

class PredictableUseCase @Inject internal constructor(private val repository: PredictableRepository) : UseCaseLiveData<Resource<PredictableEntity>, PredictableUseCase.PredictableParams, PredictableRepository>() {

    override fun getRepository(): PredictableRepository {
        return repository
    }

    override fun buildUseCaseObservable(params: PredictableParams?): LiveData<Resource<PredictableEntity>> {
        return repository.loadPredictableByCoord(
            params?.lat?.toDouble() ?: 0.0,
            params?.lon?.toDouble() ?: 0.0,
            params?.fetchRequired
                ?: false,
            units = params?.units ?: "metric"
        )
    }

    class PredictableParams(
        val lat: String = "",
        val lon: String = "",
        val fetchRequired: Boolean,
        val units: String
    ) : Params()
}
