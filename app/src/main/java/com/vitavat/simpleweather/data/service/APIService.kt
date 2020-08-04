package com.vitavat.simpleweather.data.service

import com.vitavat.simpleweather.vo.model.response.ResponseWeather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("onecall")
    fun getListWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("appid") appid: String
    ): Observable<ResponseWeather>
}