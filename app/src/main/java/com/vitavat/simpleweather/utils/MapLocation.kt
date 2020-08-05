package com.vitavat.simpleweather.utils


import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity


class MapLocation constructor(private var appCompatActivity: FragmentActivity) {

    private var mLocationManager: LocationManager? = null

    fun checkGPSStatus(): Boolean {
        val manager =
            appCompatActivity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(): Location? {
        val locationManager =
            appCompatActivity.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        val myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)

        myLocation?.let {
            return it
        } ?: kotlin.run {
            return mapGetLocationCurrent(appCompatActivity)
        }
    }

    @SuppressLint("MissingPermission")
    fun mapGetLocationCurrent(context: Context): Location? {
        mLocationManager = context.applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers = mLocationManager!!.getProviders(true)
        var bestLocation: Location? = null
        for (provider in providers) {
            val l = mLocationManager!!.getLastKnownLocation(provider) ?: continue
            if (bestLocation == null || l.accuracy < bestLocation.accuracy) {
                // Found best last known location: %s", l);
                bestLocation = l
            }
        }
        return bestLocation
    }

}