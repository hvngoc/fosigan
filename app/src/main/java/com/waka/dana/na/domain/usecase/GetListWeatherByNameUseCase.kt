package com.waka.dana.na.domain.usecase

import com.waka.dana.na.domain.model.WeatherList
import com.waka.dana.na.domain.repository.WeatherRepository

/**
 * Created by hvngoc on 7/29/22
 */
class GetListWeatherByNameUseCase(private val repository: WeatherRepository) :
    BaseUseCase<String, WeatherList>() {

    override suspend fun loadData(params: String?): WeatherList? {
        return repository.getWeather(params)
    }
}