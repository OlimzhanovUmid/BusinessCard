package com.uolimzhanov.businesscard.startup

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber
import com.uolimzhanov.businesscard.BuildConfig

class LogInitializer : Initializer<Any> {

    override fun create(context: Context): Any {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        return Any()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}