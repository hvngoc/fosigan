package com.waka.dana.na.data.di

import com.waka.dana.na.data.caches.PrefServices
import com.waka.dana.na.data.caches.PrefServicesImpl
import com.waka.dana.na.data.caches.ProviderServices
import com.waka.dana.na.data.caches.ProviderServicesImpl
import org.koin.dsl.module

/**
 * Created by hvngoc on 8/21/22
 */

val cacheModule = module {
    single<PrefServices> { PrefServicesImpl(get()) }
    single<ProviderServices> { ProviderServicesImpl(get()) }
}