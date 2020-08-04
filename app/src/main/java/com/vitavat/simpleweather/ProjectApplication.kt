package com.vitavat.simpleweather

import android.app.Application
import com.vitavat.simpleweather.di.module.networkModule
import com.vitavat.simpleweather.di.module.repositoryModule
import com.vitavat.simpleweather.di.module.utilityModule
import com.vitavat.simpleweather.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class ProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ProjectApplication)
            modules(arrayListOf(networkModule, utilityModule, repositoryModule, viewModelModule))
            androidLogger()
        }
    }
}