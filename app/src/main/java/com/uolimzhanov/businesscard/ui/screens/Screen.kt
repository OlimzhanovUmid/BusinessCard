package com.uolimzhanov.businesscard.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector){
    object Home: Screen("Home", Icons.Rounded.Home)
    object Info: Screen("Info", Icons.Rounded.Info)
    object Contacts: Screen("Contacts", Icons.Rounded.AccountCircle)
}
