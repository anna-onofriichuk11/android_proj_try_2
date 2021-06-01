package com.example.tryengapp

import android.app.Application
import com.example.tryengapp.dal.di.localDataSourceModule
import com.example.tryengapp.dal.di.networkModule
import com.example.tryengapp.dal.di.viewModelModule
import net.danlew.android.joda.JodaTimeAndroid
import org.joda.time.DateTime
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class TryEngApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setTimber()
        setKoin()
        setTime()
    }

    private fun setTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setKoin() {
        startKoin {
            androidContext(this@TryEngApp)
            androidLogger()
            modules(
                listOf(viewModelModule, networkModule, localDataSourceModule)
            )
        }
    }

    private fun setTime() {
        JodaTimeAndroid.init(this)
    }
}