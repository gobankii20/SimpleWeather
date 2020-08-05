package com.vitavat.simpleweather.di.module

import androidx.appcompat.app.AppCompatActivity
import com.vitavat.simpleweather.utils.CheckPermission
import com.vitavat.simpleweather.utils.MapLocation
import com.vitavat.simpleweather.utils.ObjectManage
import org.koin.dsl.module

val utilityModule = module {

    single { ObjectManage() }

    factory { (activity: AppCompatActivity) -> CheckPermission(activity) }

    factory { (activity: AppCompatActivity) -> MapLocation(activity) }
}
