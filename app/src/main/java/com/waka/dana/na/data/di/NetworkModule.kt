package com.waka.dana.na.data.di

import com.waka.dana.na.BuildConfig
import com.waka.dana.na.data.services.WeatherServices
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by hvngoc on 7/29/22
 */
val networkModule = module {
    single<GsonConverterFactory> { GsonConverterFactory.create() }
    single<OkHttpClient> { provideOkHttp() }
    single<Retrofit> { providesRetrofit(get(), get()) }
    single<WeatherServices> { provideWeatherService(get()) }
}

internal fun provideOkHttp(): OkHttpClient {
    val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.addHeader("Connection", "keep-alive")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    return client.build()
}

internal fun providesRetrofit(
    gsonConverterFactory: GsonConverterFactory,
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}

internal fun provideWeatherService(retrofit: Retrofit): WeatherServices {
    return retrofit.create(WeatherServices::class.java)
}