package com.dvt.weatherapp.app.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dvt.weatherapp.app.utils.extensions.hide
import com.dvt.weatherapp.app.utils.extensions.show
import com.squareup.picasso.Picasso

/**
 * Created by Masi on 2019-12-10
 */

@BindingAdapter("app:visibility")
fun setVisibilty(view: View, isVisible: Boolean) {
    view.hide()
    if (isVisible) {
        view.show()
    } else {
        view.hide()
    }
}

@BindingAdapter("app:setWeatherIcon")
fun setWeatherIcon(view: ImageView, iconPath: String?) {
    if (iconPath.isNullOrEmpty())
        return
    Picasso.get().cancelRequest(view)
    val newPath = iconPath.replace(iconPath, "a$iconPath")
    val imageid = view.context.resources.getIdentifier(newPath + "_svg", "drawable", view.context.packageName)
    val imageDrawable = view.context.resources.getDrawable(imageid, null)
    view.setImageDrawable(imageDrawable)
}
