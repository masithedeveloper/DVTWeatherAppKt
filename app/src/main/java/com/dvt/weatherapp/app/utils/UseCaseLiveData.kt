package com.dvt.weatherapp.app.utils

import androidx.lifecycle.LiveData

/**
 * Created by Masi on 2019-12-11
 */

abstract class UseCaseLiveData<M, P, R> {

    abstract fun getRepository(): R

    abstract fun buildUseCaseObservable(params: P?): LiveData<M>

    /**
     * Executes the target call
     *
     * @param params represents params to be passed
     * @return [Disposable] result
     */

    fun execute(params: P?): LiveData<M> {
        return buildUseCaseObservable(params)
    }

    abstract class Params

    class None
}
