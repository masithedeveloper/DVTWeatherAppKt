package com.dvt.weatherapp.app.base

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Masi on 2019-12-10
 */

open class BaseDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}
