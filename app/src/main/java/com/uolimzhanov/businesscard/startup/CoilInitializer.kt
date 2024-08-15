package com.uolimzhanov.businesscard.startup

import android.content.Context
import androidx.startup.Initializer
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.svg.SvgDecoder
import coil3.util.DebugLogger
import com.uolimzhanov.businesscard.BuildConfig
import kotlinx.coroutines.Dispatchers

/**
 * created by uolimzhanov on 25.02.2024
 */
class CoilInitializer : Initializer<Any> {

    override fun create(context: Context): Any {
        val cacheSize: Long = 1024 * 1024 * 50 // 50 MB
        val diskCache = DiskCache.Builder()
            .maxSizeBytes(cacheSize)
            .directory(context.cacheDir)
            .build()

        val factory = SingletonImageLoader.Factory {
            ImageLoader.Builder(it)
                .crossfade(true)
                .logger(if (BuildConfig.DEBUG) DebugLogger() else null)
                .diskCachePolicy(CachePolicy.ENABLED)
                .diskCache(diskCache)
                .coroutineContext(Dispatchers.IO)
                .components {
                    add(SvgDecoder.Factory())
                }
                .build()
        }

        SingletonImageLoader.setSafe(factory)

        return Any()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf(
        LogInitializer::class.java
    )
}