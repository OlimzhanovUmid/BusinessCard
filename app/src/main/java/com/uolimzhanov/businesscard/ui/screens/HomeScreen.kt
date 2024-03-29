package com.uolimzhanov.businesscard.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.HeartBroken
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.uolimzhanov.businesscard.R
import com.uolimzhanov.businesscard.model.BadgeEvent
import com.uolimzhanov.businesscard.ui.generic.BadgeItem
import com.uolimzhanov.businesscard.viewmodels.BadgeState
import java.util.Locale


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: BadgeState,
    onEvent: (BadgeEvent) -> Unit,
    modifier: Modifier,
    paddingValues: PaddingValues
){
    var badgeId by remember {
        mutableIntStateOf(0)
    }
    var deleteBadgeDialog by remember {
        mutableStateOf(false)
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = paddingValues,
        content = {
            items(state.badges){badge ->
                BadgeItem(
                    badge = badge,
                    modifier = Modifier
                        .padding(2.dp)
                        .combinedClickable(
                            onClick = {
                                badgeId = badge.id!!
                                onEvent(BadgeEvent.ShowBottomSheet)
                            },
                            onLongClick = {
                                badgeId = badge.id!!
                                deleteBadgeDialog = true
                            }
                        ),
                    onEvent = onEvent
                )
            }
        }
    )
    if(deleteBadgeDialog){
        val badgeToDelete = state.badges.find { it.id == badgeId }
        AlertDialog(
            onDismissRequest = { deleteBadgeDialog = !deleteBadgeDialog },
            icon = { Icon(imageVector = Icons.Default.Warning, contentDescription = "") },
            title = { Text(
                text = stringResource(R.string.delete_badge),
             ) },
            confirmButton = {
                Button(
                    onClick = {
                        badgeToDelete?.let { BadgeEvent.DeleteBadge(it) }
                            ?.let { onEvent(it) }
                        deleteBadgeDialog = !deleteBadgeDialog
                    }
                ) {
                    Text(text = stringResource(R.string.yes))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        deleteBadgeDialog = !deleteBadgeDialog
                    }
                ) {
                    Text(text = stringResource(R.string.no))
                }
            })
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    if (state.isShowingBadge) {
        val badgeToShow = state.badges.find { it.id == badgeId }
        badgeToShow?.let {
            ModalBottomSheet(
                onDismissRequest = {
                    onEvent(BadgeEvent.HideBottomSheet)
                },
                sheetState = sheetState
            ) {
                var isRussian by remember {
                    mutableStateOf(false)
                }
                isRussian = when(Locale.getDefault().language) {
                    "ru" -> true
                    else -> false
                }
                Column(
                    modifier = modifier.aspectRatio(0.66f)
                ){
                    Box(modifier = Modifier.fillMaxSize()){
                        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                            SubcomposeAsyncImage(
                                model = badgeToShow.badgeUrl,
                                contentScale = ContentScale.FillHeight,
                                error = {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.AccountCircle,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                                alpha = 0.2f
                                            ),
                                            modifier = Modifier.fillMaxSize(0.7f)
                                        )
                                    }
                                },
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(6f)
                            )
                            Text(
                                text = if(isRussian) badgeToShow.badgeNameRu else badgeToShow.badgeName,
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .weight(2f)
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            )
                            Text(
                                text = if(isRussian) badgeToShow.badgeDescriptionRu else badgeToShow.badgeDescription,
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Center,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .weight(2f)
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            )
                            Text(
                                text = badgeToShow.badgeDate,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(4.dp)
                            )
                        }
                        Icon(
                            imageVector =
                            if(badgeToShow.isFavorite)
                                Icons.Filled.Favorite
                            else
                                Icons.Outlined.HeartBroken,
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                                .size(24.dp)
                                .clickable { onEvent(BadgeEvent.UnLikeBadge(badgeToShow)) }
                        )
                    }
                }
            }
        }
    } 
}