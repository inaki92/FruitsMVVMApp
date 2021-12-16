package com.inaki.fruitmvvmapp

import android.app.Application
import com.inaki.fruitmvvmapp.di.AppModule
import com.inaki.fruitmvvmapp.di.DaggerFruitComponent
import com.inaki.fruitmvvmapp.di.FruitComponent
import com.inaki.fruitmvvmapp.di.koin.networkModule
import com.inaki.fruitmvvmapp.di.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FruitApp : Application() {

    override fun onCreate() {
        super.onCreate()

        fruitComponent = DaggerFruitComponent.builder()
            .appModule(AppModule(this))
            .build()

        /**
         * Here I am starting KOIN without the need of build the project before.
         */
        startKoin {
            // this logger, will logg data into the logcat for KOIN
            // this is optional, no need to add it
            androidLogger(Level.DEBUG)
            // This android context will provide and inject the context for you
            // This is needed when you need context to be injected (most of the time)
            androidContext(this@FruitApp)
            // these are the module that KOIN will take to provide and inject the objects
            modules(listOf(networkModule, viewModelModule))
        }
    }

    companion object {
        lateinit var fruitComponent: FruitComponent
    }
}