package com.vitavat.simpleweather.di.module

import com.vitavat.simpleweather.data.remote.OkHttpClientBuilder
import com.vitavat.simpleweather.data.remote.RetrofitBuilder
import com.vitavat.simpleweather.data.service.APIService
import org.koin.dsl.module

val networkModule = module {

    single { RetrofitBuilder }

    single<APIService> { get<RetrofitBuilder>().build(
        OkHttpClientBuilder.getUrlServer()) }
}
