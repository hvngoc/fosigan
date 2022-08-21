package com.waka.dana.na.presentation.di

import com.waka.dana.na.presentation.screen.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by hvngoc on 7/29/22
 */
val appModule = module {
    viewModel { MainViewModel(get(), get()) }
}