package com.waka.dana.na.data.cache

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.waka.dana.na.domain.model.WeatherList

/**
 * Created by hvngoc on 7/29/22
 */
class CacheServicesImpl(
    private val fileServices: FileServices,
    private val gson: Gson
) : CacheServices {
    companion object {
        private const val SUFFIX = ".waka"
    }

    override fun getWeather(query: String?): WeatherList? {
        val file = query + SUFFIX
        val data = fileServices.getData(file) ?: return null
        val type = object : TypeToken<WeatherList>() {}.type
        return gson.fromJson(data, type)
    }

    override fun saveWeather(query: String?, weatherList: WeatherList) {
        val data = gson.toJson(weatherList)
        val file = query + SUFFIX
        fileServices.saveData(file, data)
    }
}