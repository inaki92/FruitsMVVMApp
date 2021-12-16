package com.inaki.fruitmvvmapp.di.koin

import com.inaki.fruitmvvmapp.rest.NetworkApi
import com.inaki.fruitmvvmapp.viewmodel.FruitViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This is our view model module that will be injected by KOIN
 *
 * module {} is provided by koin as well as viewModel {}
 *
 * We just need to provide the view model into it
 */
val viewModelModule = module {
    viewModel {
        FruitViewModel(get())
    }
}

/**
 * This module will be the one that provides the network interfaces and objects
 */
val networkModule = module {

    /**
     * Right here, I am providing the moshi object with the KotlinJson adapter
     */
    fun provideMoshi() = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    /**
     * Here I am providing the logging interceptor
     */
    fun loggingInterceptor() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    /**
     * Here I am providing the okhttp client
     */
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()


    /**
     * Here I am providing the Retrofit object
     */
    fun provideNetworkApi(okHttpClient: OkHttpClient, moshi: Moshi) =
        Retrofit.Builder()
            .baseUrl(NetworkApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(NetworkApi::class.java)

    single { provideMoshi() }
    single { loggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideNetworkApi(get(), get()) }
}

