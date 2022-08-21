package com.waka.dana.na.domain.di

import com.waka.dana.na.domain.usecase.GetListStorageByPath
import org.koin.dsl.module

/**
 * Created by hvngoc on 7/29/22
 */
val useCaseModule = module {
    single<GetListStorageByPath> { GetListStorageByPath(get()) }
}