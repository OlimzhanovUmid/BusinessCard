package com.uolimzhanov.businesscard.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.uolimzhanov.businesscard.ui.utils.LocalWindowSizeClass

@Composable
fun AppContainer(
    windowSizeClass: WindowSizeClass
) {
    CompositionLocalProvider(LocalWindowSizeClass provides windowSizeClass) {

    }
}
