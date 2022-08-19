package com.waka.dana.na.presentation

import androidx.multidex.MultiDexApplication
import com.waka.dana.na.data.di.cacheModule
import com.waka.dana.na.data.di.networkModule
import com.waka.dana.na.data.di.repositoryModule
import com.waka.dana.na.domain.di.useCaseModule
import com.waka.dana.na.presentation.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by hvngoc on 7/29/22
 */
class MyApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule, networkModule, repositoryModule, useCaseModule, cacheModule)
        }
    }
}