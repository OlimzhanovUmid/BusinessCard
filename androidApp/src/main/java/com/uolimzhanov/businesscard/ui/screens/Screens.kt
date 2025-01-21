package com.uolimzhanov.businesscard.ui.screens

import com.uolimzhanov.businesscard.model.entity.Badge
import com.uolimzhanov.businesscard.ui.navigation.navType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
sealed interface Screens {
    @Serializable
    data object Home : Screens

    @Serializable
    data object Info : Screens

    @Serializable
    data object Contacts : Screens

    @Serializable
    data class DeleteBadge(val badge: Badge) : Screens {
        companion object {
            val typeMap = mapOf(
                typeOf<Badge>() to navType<Badge>()
            )
        }
    }
}