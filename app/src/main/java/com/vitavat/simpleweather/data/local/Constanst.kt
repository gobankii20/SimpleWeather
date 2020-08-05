package com.vitavat.simpleweather.data.local

object Constanst {

    const val TIME_CONNECT = 30L

    private const val API_ENDPOINT_SLL = "https://"

    const val URL_PRO = API_ENDPOINT_SLL + "api.openweathermap.org/data/2.5/"

    const val URL_DEV = API_ENDPOINT_SLL + "api.openweathermap.org/data/2.5/"

    const val API_KEY = "559e2b8a3ce9f347f4da05ae6542d896"

    const val UNITS = "metric"

    //const val EXCLUDE = "hourly" show time week

    const val EXCLUDE = "daily"

    const val IMAGE_URL = "https://openweathermap.org/img/w/"
}