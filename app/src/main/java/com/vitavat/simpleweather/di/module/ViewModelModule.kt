package com.vitavat.simpleweather.di.module


import com.vitavat.simpleweather.view.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }
}