package com.waka.dana.na.data.di

import com.waka.dana.na.data.repository.StorageRepositoryImpl
import com.waka.dana.na.domain.repository.StorageRepository
import org.koin.dsl.module

/**
 * Created by hvngoc on 7/29/22
 */
val repositoryModule = module {
    single<StorageRepository> { StorageRepositoryImpl(get()) }
}