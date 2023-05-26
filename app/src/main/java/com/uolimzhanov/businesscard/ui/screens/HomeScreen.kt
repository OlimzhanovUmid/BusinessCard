package com.uolimzhanov.businesscard.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uolimzhanov.businesscard.model.BadgeEvent
import com.uolimzhanov.businesscard.ui.generic.BadgeItem
import com.uolimzhanov.businesscard.viewmodels.BadgeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: BadgeState,
    onEvent: (BadgeEvent) -> Unit,
    nestedScroll: Modifier,
    paddingValues: PaddingValues
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier,
        contentPadding = paddingValues,
        content = {
            items(state.badges.size) {
                state.badges.forEach { badge ->
                    BadgeItem(
                        badge = badge,
                        modifier = Modifier.clickable {
                            onEvent(BadgeEvent.ShowBottomSheet)
                        }
                    )
                }
            }
        }
    )
    if(state.isShowingBadge){
        ModalBottomSheet(
            onDismissRequest = {
                onEvent(BadgeEvent.HideBottomSheet)
            }) {
        }
    }
}