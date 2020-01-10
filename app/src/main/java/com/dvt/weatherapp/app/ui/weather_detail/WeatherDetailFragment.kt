package com.dvt.weatherapp.app.ui.weather_detail

import android.transition.TransitionInflater
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dvt.weatherapp.app.R
import com.dvt.weatherapp.app.base.BaseFragment
import com.dvt.weatherapp.app.databinding.FragmentWeatherDetailBinding
import com.dvt.weatherapp.app.di.Injectable
import com.dvt.weatherapp.app.domain.model.ListItem
import com.dvt.weatherapp.app.ui.weather_detail.weatherHourOfDay.WeatherHourOfDayAdapter
import com.mikhaellopez.rxanimation.RxAnimation
import com.mikhaellopez.rxanimation.fadeOut
import com.mikhaellopez.rxanimation.resize
import com.mikhaellopez.rxanimation.translation
import io.reactivex.disposables.CompositeDisposable

class WeatherDetailFragment : BaseFragment<WeatherDetailViewModel, FragmentWeatherDetailBinding>(WeatherDetailViewModel::class.java), Injectable {

    private val weatherDetailFragmentArgs: WeatherDetailFragmentArgs by navArgs()
    var disposable = CompositeDisposable()

    override fun getLayoutRes(): Int {
        return R.layout.fragment_weather_detail
    }

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()
        viewModel.weatherItem.set(weatherDetailFragmentArgs.weatherItem)
        viewModel.selectedDayDate = weatherDetailFragmentArgs.weatherItem.dtTxt?.substringBefore(" ")

        viewModel.getPredictableLiveData().observe(
            viewLifecycleOwner,
            Observer {
                viewModel.selectedDayPredictableLiveData.postValue(it.list?.filter { it.dtTxt?.substringBefore(" ") == viewModel.selectedDayDate })
            }
        )

        viewModel.selectedDayPredictableLiveData.observe(
            viewLifecycleOwner,
            Observer {
                initWeatherHourOfDayAdapter(it)
            }
        )

        val transition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
        handleBackPressed()

        mBinding.fabClose.setOnClickListener {
            disposable.add(
                RxAnimation.together(
                    mBinding.fabClose.fadeOut(350L),
                    mBinding.cardView.resize(0, 0, 350L),
                    mBinding.cardView.translation(-250f, 1000f, 350L)
                )
                    .doOnTerminate { findNavController().popBackStack() }
                    .subscribe()
            )
        }
    }

    private fun initWeatherHourOfDayAdapter(list: List<ListItem>) {
        val adapter = WeatherHourOfDayAdapter { item ->
        }

        mBinding.recyclerViewHourOfDay.adapter = adapter
        (mBinding.recyclerViewHourOfDay.adapter as WeatherHourOfDayAdapter).submitList(list)
    }

    private fun handleBackPressed() {
        // This callback will only be called when WeatherDetailFragment is at least Started.
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                disposable.add(
                    RxAnimation.together(
                        mBinding.fabClose.fadeOut(350L),
                        mBinding.cardView.resize(0, 0, 350L),
                        mBinding.cardView.translation(-250f, 1000f, 350L)
                    )
                        .doOnTerminate { findNavController().popBackStack() }
                        .subscribe()
                )
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable.clear()
    }
}
