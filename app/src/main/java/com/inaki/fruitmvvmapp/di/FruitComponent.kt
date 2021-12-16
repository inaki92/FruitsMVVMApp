package com.inaki.fruitmvvmapp.di

import com.inaki.fruitmvvmapp.MainActivity
import com.inaki.fruitmvvmapp.viewmodel.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    ViewModelModule::class
])
@Singleton
interface FruitComponent {
    // TODO add inject method when UI is ready to be developed
    fun viewModelsFactory(): ViewModelFactory

    fun inject(mainActivity: MainActivity)
}