package com.vitavat.simpleweather.utils

import android.location.Location
import com.vitavat.simpleweather.data.local.Constanst
import com.vitavat.simpleweather.vo.model.body.BodyWeather

class ObjectManage {

    fun createObjWeather(currentLocation: Location?): BodyWeather {
        return BodyWeather(
            currentLocation?.latitude ?: 0.0,
            currentLocation?.longitude ?: 0.0,
            Constanst.EXCLUDE,
            Constanst.UNITS,
            Constanst.API_KEY
        )
    }
}