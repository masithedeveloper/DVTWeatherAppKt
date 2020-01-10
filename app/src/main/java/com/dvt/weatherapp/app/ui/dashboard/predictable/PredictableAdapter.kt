package com.dvt.weatherapp.app.ui.dashboard.predictable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.dvt.weatherapp.app.R
import com.dvt.weatherapp.app.adapter.BaseAdapter
import com.dvt.weatherapp.app.databinding.ItemPredictableBinding
import com.dvt.weatherapp.app.domain.model.ListItem

/**
 * Created by Masi on 2019-12-11
 */

class PredictableAdapter(private val callBack: (ListItem, View, View, View, View, View) -> Unit) : BaseAdapter<ListItem>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = DataBindingUtil.inflate<ItemPredictableBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_predictable,
            parent,
            false
        )
        val viewModel = PredictableItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                ViewCompat.setTransitionName(mBinding.cardView, "weatherItem")
                ViewCompat.setTransitionName(mBinding.imageViewPredictableIcon, "weatherItemPredictableIcon")
                ViewCompat.setTransitionName(mBinding.textViewDayOfWeek, "weatherItemDayOfWeek")
                ViewCompat.setTransitionName(mBinding.textViewTemp, "weatherItemTemp")
                ViewCompat.setTransitionName(mBinding.linearLayoutTempMaxMin, "weatherItemTempMaxMin")
                callBack.invoke(it, mBinding.cardView, mBinding.imageViewPredictableIcon, mBinding.textViewDayOfWeek, mBinding.textViewTemp, mBinding.linearLayoutTempMaxMin)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemPredictableBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<ListItem>() {
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.dtTxt == newItem.dtTxt
}
