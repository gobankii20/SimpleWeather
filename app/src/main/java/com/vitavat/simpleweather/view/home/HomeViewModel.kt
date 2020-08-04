package com.vitavat.simpleweather.view.home

import androidx.lifecycle.ViewModel
import com.vitavat.simpleweather.utils.SingleLiveData

class HomeViewModel constructor() : ViewModel() {

    val onClickItemOrderList = SingleLiveData<String>()

//    val mOrderBookingCall = SingleLiveData<Void>()
//    val mResponseOrderBooking = Transformations.switchMap(mOrderBookingCall) {
//        generalUseCase.getOrderList(mCurrentPage.get()!!)
//    }
}