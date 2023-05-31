package com.uolimzhanov.businesscard.startup

import android.content.Context
import androidx.startup.Initializer
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.util.DebugLogger
import com.uolimzhanov.businesscard.BuildConfig
import java.io.File

class CoilInitializer : Initializer<Any> {

    override fun create(context: Context): Any {
        val cacheDir = File(context.cacheDir, "image_cache")
        cacheDir.mkdirs()

        val cacheSize: Long = 1024L * 1024L * 50L // 50 MB
        val cache = DiskCache.Builder()
            .directory(cacheDir)
            .maxSizeBytes(cacheSize)
            .build()

        val loader = ImageLoader.Builder(context)
            .crossfade(true)
            .logger(if (BuildConfig.DEBUG) DebugLogger() else null)
            .diskCache(cache)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()

        Coil.setImageLoader(loader)

        return Any()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf(
        LogInitializer::class.java
    )
}