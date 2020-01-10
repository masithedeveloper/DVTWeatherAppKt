package com.dvt.weatherapp.app.ui.dashboard

import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dvt.weatherapp.app.R
import com.dvt.weatherapp.app.base.BaseFragment
import com.dvt.weatherapp.app.databinding.FragmentDashboardBinding
import com.dvt.weatherapp.app.di.Injectable
import com.dvt.weatherapp.app.domain.model.ListItem
import com.dvt.weatherapp.app.domain.usecase.PresentWeatherUseCase
import com.dvt.weatherapp.app.domain.usecase.PredictableUseCase
import com.dvt.weatherapp.app.ui.dashboard.predictable.PredictableAdapter
import com.dvt.weatherapp.app.ui.main.MainActivity
import com.dvt.weatherapp.app.utils.extensions.isNetworkAvailable

class DashboardFragment : BaseFragment<DashboardFragmentViewModel, FragmentDashboardBinding>(DashboardFragmentViewModel::class.java), Injectable {

    override fun getLayoutRes() = R.layout.fragment_dashboard

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()
        initPredictableAdapter()

        val lat: String? = viewModel.sharedPreferences.getString("lat", "")
        val lon: String? = viewModel.sharedPreferences.getString("lon", "")

        if (lat?.isNotEmpty() == true && lon?.isNotEmpty() == true) {
            viewModel.presentWeatherParams.postValue(PresentWeatherUseCase.PresentWeatherParams(lat, lon, isNetworkAvailable(requireContext()), "metric"))
            viewModel.predictableParams.postValue(PredictableUseCase.PredictableParams(lat, lon, isNetworkAvailable(requireContext()), "metric"))
        }

        viewModel.getPredictableViewState().observe(
            viewLifecycleOwner,
            Observer {
                with(mBinding) {
                    viewState = it
                    it.data?.list?.let { predictables -> initPredictable(predictables) }
                    (activity as MainActivity).viewModel.toolbarTitle.set(it.data?.city?.getCityAndCountry())
                }
            }
        )

        viewModel.getPresentWeatherViewState().observe(
            viewLifecycleOwner,
            Observer {
                with(mBinding) {
                    containerPredictable.viewState = it
                }
            }
        )
    }

    private fun initPredictableAdapter() {
        val adapter = PredictableAdapter { item, cardView, predictableIcon, dayOfWeek, temp, tempMaxMin ->
            val action = DashboardFragmentDirections.actionDashboardFragmentToWeatherDetailFragment(item)
            findNavController()
                .navigate(
                    action,
                    FragmentNavigator.Extras.Builder()
                        .addSharedElements(
                            mapOf(
                                cardView to "weatherItemCardView",
                                predictableIcon to "weatherItemPredictableIcon",
                                dayOfWeek to "weatherItemDayOfWeek",
                                temp to "weatherItemTemp",
                                tempMaxMin to "weatherItemTempMaxMin"
                            )
                        )
                        .build()
                )
        }

        mBinding.recyclerPredictable.adapter = adapter
        mBinding.recyclerPredictable.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initPredictable(list: List<ListItem>) {
        (mBinding.recyclerPredictable.adapter as PredictableAdapter).submitList(PredictableMapper().mapFrom(list))
    }
}
