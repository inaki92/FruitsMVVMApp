package com.inaki.fruitmvvmapp

import android.app.Application
import com.inaki.fruitmvvmapp.di.AppModule
import com.inaki.fruitmvvmapp.di.DaggerFruitComponent
import com.inaki.fruitmvvmapp.di.FruitComponent

class FruitApp : Application() {

    override fun onCreate() {
        super.onCreate()

        fruitComponent = DaggerFruitComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var fruitComponent: FruitComponent
    }
}