package com.uolimzhanov.businesscard.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uolimzhanov.businesscard.model.repository.UserRepository
import com.uolimzhanov.businesscard.ui.screens.ContactsScreen
import com.uolimzhanov.businesscard.ui.screens.HomeScreen
import com.uolimzhanov.businesscard.ui.screens.InfoScreen
import com.uolimzhanov.businesscard.ui.screens.Screen
import com.uolimzhanov.businesscard.viewmodels.BadgeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContainer() {
    val viewModel = hiltViewModel<BadgeViewModel>()
    val navController = rememberNavController()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val state by viewModel.state.collectAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination =
        navBackStackEntry?.destination?.route ?: Screen.Home.route
    val screens = listOf(
        Screen.Home,
        Screen.Info,
        Screen.Contacts
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                screens.forEach { screen ->
                    NavigationBarItem(
                        selected = selectedDestination == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(imageVector = screen.icon, contentDescription = screen.route)
                        },
                        label = {
                            Text(text = stringResource(screen.textId))
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        val paddingValues = PaddingValues(
            start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
            end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
            bottom = innerPadding.calculateBottomPadding()
        )
        
        NavHost(navController = navController, startDestination = Screen.Home.route) {
            composable(
                route = Screen.Home.route,
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                HomeScreen(
                    modifier = Modifier
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .padding(paddingValues)
                        .fillMaxSize(),
                    state = state,
                    onEvent = viewModel::onEvent,
                    scrollBehavior = scrollBehavior
                )
            }
            composable(route = Screen.Info.route,
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                InfoScreen(
                    modifier = Modifier
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .fillMaxSize()
                        .padding(paddingValues),
                    scrollBehavior = scrollBehavior
                )
            }
            composable(
                route = Screen.Contacts.route,
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                ContactsScreen(
                    modifier = Modifier
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .fillMaxSize()
                        .padding(paddingValues),
                    user = UserRepository.user,
                    scrollBehavior = scrollBehavior
                )
            }
        }
    }
}
