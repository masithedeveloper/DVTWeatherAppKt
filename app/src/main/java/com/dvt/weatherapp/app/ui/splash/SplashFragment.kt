package com.dvt.weatherapp.app.ui.splash

import android.graphics.Color
import androidx.navigation.fragment.findNavController
import com.dvt.weatherapp.app.R
import com.dvt.weatherapp.app.base.BaseFragment
import com.dvt.weatherapp.app.databinding.FragmentSplashBinding
import com.dvt.weatherapp.app.di.Injectable
import com.dvt.weatherapp.app.utils.extensions.hide
import com.dvt.weatherapp.app.utils.extensions.show
import com.mikhaellopez.rxanimation.*
import io.reactivex.disposables.CompositeDisposable

class SplashFragment : BaseFragment<SplashFragmentViewModel, FragmentSplashBinding>(SplashFragmentViewModel::class.java), Injectable {

    var disposable = CompositeDisposable()

    override fun getLayoutRes() = R.layout.fragment_splash

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()

        if (viewModel.sharedPreferences.getString("lat", "").isNullOrEmpty()) {
            mBinding.buttonExplore.show()
            viewModel.navigateDashboard = false
        } else {
            mBinding.buttonExplore.hide()
            viewModel.navigateDashboard = true
        }

        startSplashAnimation(viewModel.navigateDashboard)

        mBinding.buttonExplore.setOnClickListener {
            endSplashAnimation(viewModel.navigateDashboard)
        }

        mBinding.rootView.setOnClickListener {
            endSplashAnimation(viewModel.navigateDashboard)
        }
    }

    private fun startSplashAnimation(navigateToDashboard: Boolean) {
        disposable.add(
            RxAnimation.sequentially(
                RxAnimation.together(
                    mBinding.imageViewBottomDrawable.translationY(0f),
                    mBinding.imageViewEllipse.fadeOut(0L),
                    mBinding.imageViewBottomDrawable.fadeOut(0L),
                    mBinding.imageViewBigCloud.translationX(-0F, 0L),
                    mBinding.imageViewSmallCloud.translationX(0f, 0L),
                    mBinding.imageViewBottomDrawableShadow.translationY(0f),
                    mBinding.imageViewMainCloud.fadeOut(0L),
                    mBinding.buttonExplore.fadeOut(0L),
                    mBinding.imageViewBottomDrawableShadow.fadeOut(0L)
                ),

                RxAnimation.together(
                    mBinding.imageViewBottomDrawable.fadeIn(0L),
                    mBinding.imageViewBottomDrawable.translationY(-1f),
                    mBinding.imageViewBottomDrawableShadow.fadeIn(0L),
                    mBinding.imageViewBottomDrawableShadow.translationY(-1f)
                ),

                RxAnimation.together(
                    mBinding.imageViewEllipse.fadeIn(0L),
                    mBinding.imageViewEllipse.translationY(-50F, 0L)
                ),

                RxAnimation.together(
                    mBinding.imageViewBigCloud.translationX(-15f, 0L),
                    mBinding.imageViewSmallCloud.translationX(25f, 0L)
                ),

                mBinding.imageViewMainCloud.fadeIn(0L),
                mBinding.buttonExplore.fadeIn(0L)
            ).doOnTerminate {
                findNavController().graph.startDestination = R.id.dashboardFragment // Little bit tricky solution :)
                if (navigateToDashboard)
                    endSplashAnimation(navigateToDashboard)
            }
                .subscribe()
        )
    }

    private fun endSplashAnimation(navigateToDashboard: Boolean) {
        disposable.add(
            RxAnimation.sequentially(
                RxAnimation.together(
                    mBinding.imageViewBottomDrawable.fadeOut(300L),
                    mBinding.imageViewBottomDrawable.translationY(100f),
                    mBinding.imageViewBottomDrawableShadow.fadeOut(300L),
                    mBinding.imageViewBottomDrawableShadow.translationY(100f)
                ),

                RxAnimation.together(
                    mBinding.imageViewEllipse.fadeOut(300L),
                    mBinding.imageViewEllipse.translationY(500F, 300L)
                ),

                RxAnimation.together(
                    mBinding.imageViewBigCloud.translationX(500f, 300L),
                    mBinding.imageViewSmallCloud.translationX(-500f, 300L)
                ),

                mBinding.imageViewMainCloud.fadeOut(300L),
                mBinding.buttonExplore.fadeOut(300L),
                mBinding.rootView.backgroundColor(
                    Color.parseColor("#5D50FE"),
                    Color.parseColor("#FFFFFF"),
                    duration = 750L
                )
            )
                .doOnTerminate {
                    findNavController().graph.startDestination = R.id.dashboardFragment
                    if (navigateToDashboard)
                        findNavController()
                            .navigate(SplashFragmentDirections.actionSplashFragmentToDashboardFragment())
                    else
                        findNavController().navigate(R.id.action_splashFragment_to_searchFragment)
                }
                .subscribe()

        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
