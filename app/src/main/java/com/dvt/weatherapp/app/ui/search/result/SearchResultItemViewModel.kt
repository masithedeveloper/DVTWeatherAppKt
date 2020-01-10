package com.dvt.weatherapp.app.ui.search.result

import androidx.databinding.ObservableField
import com.dvt.weatherapp.app.base.BaseViewModel
import com.dvt.weatherapp.app.domain.repo.entity.CitiesForSearchEntity
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-11
 */

class SearchResultItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<CitiesForSearchEntity>()
}
