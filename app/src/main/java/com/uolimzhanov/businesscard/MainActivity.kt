package com.uolimzhanov.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.uolimzhanov.businesscard.di.allModules
import com.uolimzhanov.businesscard.ui.AppContainer
import com.uolimzhanov.businesscard.ui.theme.BusinessCardTheme
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()

        setContent {
            KoinApplication(application = { modules(allModules) }) {
                BusinessCardTheme {
                    AppContainer()
                }
            }
        }
    }
}