package com.vitavat.simpleweather.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.vitavat.simpleweather.R
import com.vitavat.simpleweather.data.local.Constanst
import com.vitavat.simpleweather.databinding.DialogAlertMessageBinding

fun View.showMessage(message: String) {
    val snack = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snack.show()
}


fun Context.dialogMessage(text: String) {
    val dialogMessage = Dialog(this)

    dialogMessage.setCanceledOnTouchOutside(false)
    val binding: DialogAlertMessageBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.dialog_alert_message, null, false
        )
    dialogMessage.setContentView(binding.root)

    binding.text = text

    binding.tvOkey.setOnClickListener {
        dialogMessage.dismiss()
    }

    dialogMessage.show()
}


fun Context.setImageView(view: ImageView, url:String){
    Glide.with(this)
        .load("${Constanst.IMAGE_URL}${url}.png")
        .into(view)
}