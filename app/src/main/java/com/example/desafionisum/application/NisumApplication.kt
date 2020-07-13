package com.example.desafionisum.application

import android.app.Application
import com.example.data.network.di.networkModule
import com.example.data.preference.di.preferenceModule
import com.example.desafionisum.di.viewModelsModule
import com.example.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NisumApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@NisumApplication)
            modules(listOf(preferenceModule, networkModule, domainModule, viewModelsModule))
        }

    }
}