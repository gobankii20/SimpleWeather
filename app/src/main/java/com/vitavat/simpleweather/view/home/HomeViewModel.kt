package com.vitavat.simpleweather.view.home

import androidx.databinding.ObservableField
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vitavat.simpleweather.data.remote.repository.GeneralRepository
import com.vitavat.simpleweather.utils.SingleLiveData
import com.vitavat.simpleweather.vo.model.body.BodyWeather

class HomeViewModel constructor(generalRepository: GeneralRepository) : ViewModel() {

    val mDateCurrent = ObservableField("")

    val mWeatherDescription = ObservableField("")

    val mSeeWeatherTxt = ObservableField("Humidity")

    val isCheckModeWeather = ObservableField(true)

    val onClickItemOrderList = SingleLiveData<String>()

    val mWeatherListCall = SingleLiveData<BodyWeather>()
    val mResponseWeather = Transformations.switchMap(mWeatherListCall) {
        generalRepository.getListWeather(it)
    }
}