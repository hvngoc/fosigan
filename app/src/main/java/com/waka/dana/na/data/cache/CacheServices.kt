package com.waka.dana.na.data.cache

import com.waka.dana.na.domain.model.WeatherList

/**
 * Created by hvngoc on 7/29/22
 */
interface CacheServices {
    fun getWeather(query: String?): WeatherList?

    fun saveWeather(query: String?, weatherList: WeatherList)

}