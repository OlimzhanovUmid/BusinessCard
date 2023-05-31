package com.uolimzhanov.businesscard.ui

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.uolimzhanov.businesscard.R
import com.uolimzhanov.businesscard.model.BadgeEvent
import com.uolimzhanov.businesscard.model.repository.UserRepository
import com.uolimzhanov.businesscard.ui.screens.ContactsScreen
import com.uolimzhanov.businesscard.ui.screens.HomeScreen
import com.uolimzhanov.businesscard.ui.screens.InfoScreen
import com.uolimzhanov.businesscard.ui.screens.Screen
import com.uolimzhanov.businesscard.ui.screens.SortOrderMenu
import com.uolimzhanov.businesscard.viewmodels.BadgeViewModel
import timber.log.Timber

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AppContainer(
) {
    val viewModel = hiltViewModel<BadgeViewModel>()
    val navController = rememberAnimatedNavController()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val state by viewModel.state.collectAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination =
        navBackStackEntry?.destination?.route ?: Screen.Home.route
    var sortMenuExpanded by remember { mutableStateOf(false) }
    Timber.tag("State").d(state.badges.toString())
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = selectedDestination
                    )
                },
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                scrollBehavior = scrollBehavior,
                actions = {
                    Box {
                        IconButton(onClick = { sortMenuExpanded = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.sort_variant),
                                contentDescription = null
                            )
                        }
                        SortOrderMenu(
                            isExpanded = sortMenuExpanded,
                            selectedOrder = state.sortOrder,
                            onDismiss = { sortMenuExpanded = false },
                            onItemSelected = {
                                viewModel.onEvent(BadgeEvent.SortBadges(it))
                            }
                        )
                    }
                }
            )
        },
        bottomBar = {
            val items = listOf(
                Screen.Home,
                Screen.Info,
                Screen.Contacts
            )
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = selectedDestination == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.route)
                        },
                        label = {
                            Text(text = item.route)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        AnimatedNavHost(navController = navController, startDestination = Screen.Home.route) {
            composable(
                route = Screen.Home.route,
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                HomeScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    paddingValues = paddingValues
                )
            }
            composable(route = Screen.Info.route,
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                InfoScreen(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    paddingValues = paddingValues
                )
            }
            composable(
                route = Screen.Contacts.route,
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                ContactsScreen(
                    user = UserRepository.user,
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    paddingValues = paddingValues
                )
            }
        }
    }
}
