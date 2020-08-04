package com.vitavat.simpleweather.di.module

import com.vitavat.simpleweather.data.remote.repository.GeneralRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { GeneralRepository(get()) }

}