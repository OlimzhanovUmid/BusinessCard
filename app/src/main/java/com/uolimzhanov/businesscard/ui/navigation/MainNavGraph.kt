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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uolimzhanov.businesscard.model.repository.UserRepository
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
    paddingValues: PaddingValues,
    scrollBehavior: TopAppBarScrollBehavior
) {
    composable<Screens.Home>(
        enterTransition = { fadeIn(animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) }
    ) {
        val viewModel = hiltViewModel<BadgeViewModel>(it)
        val state by viewModel.state.collectAsStateWithLifecycle()

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