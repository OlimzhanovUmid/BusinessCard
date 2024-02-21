package com.uolimzhanov.businesscard.ui.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.uolimzhanov.businesscard.R

sealed class Screen(val route: String, val icon: ImageVector, @StringRes val textId: Int){
    data object Home: Screen("Home", Icons.Rounded.Home, R.string.home)
    data object Info: Screen("Info", Icons.Rounded.Info, R.string.info)
    data object Contacts: Screen("Contacts", Icons.Rounded.AccountCircle, R.string.contacts)
}
