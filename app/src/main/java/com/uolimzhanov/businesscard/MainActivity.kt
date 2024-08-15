package com.uolimzhanov.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.uolimzhanov.businesscard.ui.AppContainer
import com.uolimzhanov.businesscard.ui.theme.BusinessCardTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            BusinessCardTheme {
                AppContainer()
            }
        }
    }
}