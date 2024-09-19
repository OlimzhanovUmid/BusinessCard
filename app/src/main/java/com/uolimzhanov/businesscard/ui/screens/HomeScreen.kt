package com.uolimzhanov.businesscard.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uolimzhanov.businesscard.R
import com.uolimzhanov.businesscard.model.BadgeEvent
import com.uolimzhanov.businesscard.model.entity.Badge
import com.uolimzhanov.businesscard.ui.generic.BadgeBottomSheet
import com.uolimzhanov.businesscard.ui.generic.BadgeItem
import com.uolimzhanov.businesscard.viewmodels.BadgeState

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: BadgeState = BadgeState(),
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    onEvent: (BadgeEvent) -> Unit = {},
    onDeleteBadge: (Badge) -> Unit = {}
) {
    var sortMenuExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    Column(modifier) {
        LargeTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.home)
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
                        onItemSelected = { onEvent(BadgeEvent.SortBadges(it)) }
                    )
                }
            }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            content = {
                items(state.badges) { badge ->
                    BadgeItem(
                        badge = badge,
                        modifier = Modifier
                            .padding(2.dp)
                            .combinedClickable(
                                onClick = {
                                    onEvent(BadgeEvent.ShowBottomSheet(badge))
                                },
                                onLongClick = {
                                    onDeleteBadge(badge)
                                }
                            ),
                        onEvent = onEvent
                    )
                }
            }
        )
    }
    
    if (state.isShowingBadge) {
        state.selectedBadge?.let {
            BadgeBottomSheet(
                badge = it,
                onUnLikeBadge = { badge ->
                    onEvent(BadgeEvent.UnLikeBadge(badge))
                },
                onHide = {
                    onEvent(BadgeEvent.HideBottomSheet)
                }
            )
        }
    }
}