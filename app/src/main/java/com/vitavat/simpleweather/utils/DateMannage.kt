package com.vitavat.simpleweather.utils

import android.text.format.DateFormat
import java.util.*

object DateManage {

    fun timeStampToDateFormat(time: Long): String {
        val DATE_FORMAT = "HH:mm"
        val cal: Calendar = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = time * 1000
        return DateFormat.format(DATE_FORMAT, cal).toString()
    }

    fun timeStampToDateCurrent(time: Long): String {
        val DATE_FORMAT = "EEE  dd MMMM HH:mm"
        val cal: Calendar = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = time * 1000
        return DateFormat.format(DATE_FORMAT, cal).toString()
    }
}