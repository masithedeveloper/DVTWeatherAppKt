package com.dvt.weatherapp.app.ui.dashboard.predictable

import androidx.databinding.ObservableField
import com.dvt.weatherapp.app.base.BaseViewModel
import com.dvt.weatherapp.app.domain.model.ListItem
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-11
 */

class PredictableItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<ListItem>()
}
