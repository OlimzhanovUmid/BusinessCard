package com.uolimzhanov.businesscard.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.toRoute
import com.uolimzhanov.businesscard.model.BadgeEvent
import com.uolimzhanov.businesscard.model.repository.UserRepository
import com.uolimzhanov.businesscard.ui.generic.BadgeDeletionDialog
import com.uolimzhanov.businesscard.ui.screens.ContactsScreen
import com.uolimzhanov.businesscard.ui.screens.HomeScreen
import com.uolimzhanov.businesscard.ui.screens.InfoScreen
import com.uolimzhanov.businesscard.ui.screens.Screens
import com.uolimzhanov.businesscard.viewmodels.BadgeViewModel

/**
 * Created by uolimzhanov on 19.09.2024
 */
@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
    paddingValues: PaddingValues
) {
    composable<Screens.Home>(
        enterTransition = { fadeIn(animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) }
    ) { entry ->
        val viewModel = hiltViewModel<BadgeViewModel>(entry)
        val state by viewModel.state.collectAsStateWithLifecycle()

        HomeScreen(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(paddingValues)
                .fillMaxSize(),
            state = state,
            onEvent = viewModel::onEvent,
            onDeleteBadge = {
                navController.navigate(Screens.DeleteBadge(it))
            },
            scrollBehavior = scrollBehavior
        )

    }
    dialog<Screens.DeleteBadge>(
        typeMap = Screens.DeleteBadge.typeMap
    ) { dialog ->
        val badge = dialog.toRoute<Screens.DeleteBadge>().badge
        val parentEntry = remember(dialog) {
            navController.getBackStackEntry(Screens.Home)
        }
        val viewModel = hiltViewModel<BadgeViewModel>(parentEntry)

        BadgeDeletionDialog(
            badge = badge,
            onConfirm = {
                viewModel.onEvent(BadgeEvent.DeleteBadge(it))
                navController.navigateUp()
            },
            onDismiss = {
                navController.navigateUp()
            }
        )
    }
    composable<Screens.Info>(
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
    composable<Screens.Contacts>(
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