package com.waka.dana.na.domain.repository

import com.waka.dana.na.domain.model.WeatherList

/**
 * Created by hvngoc on 7/29/22
 */
interface WeatherRepository {

    @Throws(Throwable::class)
    fun getWeather(query: String?): WeatherList
}