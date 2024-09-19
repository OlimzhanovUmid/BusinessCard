package com.uolimzhanov.businesscard.ui.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Created by uolimzhanov on 19.09.2024
 */
inline fun <reified T> navType(
    isNullableAllowed: Boolean = false,
) = object : NavType<T?>(isNullableAllowed) {
    override fun get(bundle: Bundle, key: String): T? {
        val value = bundle.getString(key) ?: return null
        return Json.decodeFromString(value)
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: T?): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: T?) {
        bundle.putString(key, Json.encodeToString(value))
    }
}