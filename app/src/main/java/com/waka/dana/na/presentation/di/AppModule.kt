package com.waka.dana.na.presentation.di

import com.waka.dana.na.presentation.screen.MainViewModel
import com.waka.dana.na.presentation.screen.search.filter.FilterViewModel
import com.waka.dana.na.presentation.screen.search.all.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by hvngoc on 7/29/22
 */
val appModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { FilterViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}