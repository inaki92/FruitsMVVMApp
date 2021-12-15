package com.inaki.fruitmvvmapp.di

import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    AppModule::class,
    NetworkModule::class
])
@Singleton
interface FruitComponent {
    // TODO add inject method when UI is ready to be developed
}