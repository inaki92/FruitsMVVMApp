package com.inaki.fruitmvvmapp.di

import com.inaki.fruitmvvmapp.rest.NetworkApi
import com.inaki.fruitmvvmapp.viewmodel.FruitViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideViewModel(fruitsApi: NetworkApi): FruitViewModel {
        return FruitViewModel(fruitsApi)
    }
}