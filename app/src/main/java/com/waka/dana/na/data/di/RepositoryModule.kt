package com.waka.dana.na.data.di

import com.waka.dana.na.data.repository.WeatherRepositoryImpl
import com.waka.dana.na.domain.repository.WeatherRepository
import org.koin.dsl.module

/**
 * Created by hvngoc on 7/29/22
 */
val repositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get(), get()) }
}