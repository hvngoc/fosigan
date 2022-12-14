package com.waka.dana.na.domain.di

import com.waka.dana.na.domain.usecase.GetListStorageByPath
import com.waka.dana.na.domain.usecase.SearchStorageByName
import com.waka.dana.na.domain.usecase.SearchStorageInPath
import org.koin.dsl.module

/**
 * Created by hvngoc on 7/29/22
 */
val useCaseModule = module {
    single<GetListStorageByPath> { GetListStorageByPath(get()) }
    single<SearchStorageInPath> { SearchStorageInPath(get()) }
    single<SearchStorageByName> { SearchStorageByName(get()) }
}