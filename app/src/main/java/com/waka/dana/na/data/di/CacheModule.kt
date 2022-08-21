package com.waka.dana.na.data.di

import com.waka.dana.na.data.caches.PrefServices
import com.waka.dana.na.data.caches.PrefServicesImpl
import org.koin.dsl.module

/**
 * Created by hvngoc on 8/21/22
 */

val cacheModule = module {
    single<PrefServices> { PrefServicesImpl(get()) }
}