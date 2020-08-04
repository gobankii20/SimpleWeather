package com.vitavat.simpleweather.data.remote.repository

import com.vitavat.simpleweather.data.remote.NetworkBoundResource
import com.vitavat.simpleweather.data.service.APIService
import com.vitavat.simpleweather.vo.model.body.BodyWeather
import com.vitavat.simpleweather.vo.model.response.ResponseWeather

class GeneralRepository constructor(val apiService: APIService) {

    fun getListNews(bodyWeather: BodyWeather) = object : NetworkBoundResource<ResponseWeather>() {
        override fun createCall() = apiService.getListWeather(
            bodyWeather.lat, bodyWeather.lon,
            bodyWeather.exclude, bodyWeather.appid
        )
    }.asLiveData()
}