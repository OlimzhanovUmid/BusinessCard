package com.uolimzhanov.businesscard.ui.screens

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screens {
    @Serializable
    data object Home : Screens

    @Serializable
    data object Info : Screens

    @Serializable
    data object Contacts : Screens
}