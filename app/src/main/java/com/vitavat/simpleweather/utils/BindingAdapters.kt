package com.vitavat.simpleweather.utils

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @JvmStatic
    @BindingAdapter(value = ["visibleGone"], requireAll = false)
    fun showHide(view: View, visibleGone: Boolean) {
        view.visibility = if (visibleGone) View.VISIBLE else View.GONE
    }
}
