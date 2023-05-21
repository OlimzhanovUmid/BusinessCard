package com.uolimzhanov.businesscard.ui.utils

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.compositionLocalOf

val LocalWindowSizeClass =
    compositionLocalOf<WindowSizeClass> { error("No default window size class!") }