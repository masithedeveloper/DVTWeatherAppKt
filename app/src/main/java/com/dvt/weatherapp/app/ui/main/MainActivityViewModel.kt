package com.dvt.weatherapp.app.ui.main

import androidx.databinding.ObservableField
import com.dvt.weatherapp.app.base.BaseViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject internal constructor() : BaseViewModel() {
    var toolbarTitle: ObservableField<String> = ObservableField()
}
