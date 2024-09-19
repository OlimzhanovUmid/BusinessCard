package com.uolimzhanov.businesscard.ui.generic

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.HeartBroken
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.uolimzhanov.businesscard.model.entity.Badge
import java.util.Locale

/**
 * Created by uolimzhanov on 20.09.2024
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BadgeBottomSheet(
    modifier: Modifier = Modifier,
    badge: Badge,
    sheetState: SheetState = rememberModalBottomSheetState(true),
    onUnLikeBadge: (Badge) -> Unit = {},
    onHide: () -> Unit = {}
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onHide,
        sheetState = sheetState
    ) {
        var isRussian by remember {
            mutableStateOf(false)
        }
        isRussian = when (Locale.getDefault().language) {
            "ru" -> true
            else -> false
        }
        Box(modifier = Modifier.aspectRatio(0.7f)) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                BadgeImage(
                    badge = badge,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(6f)
                )
                Text(
                    text = if (isRussian) badge.badgeNameRu else badge.badgeName,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .padding(4.dp)
                )
                Text(
                    text = if (isRussian) badge.badgeDescriptionRu else badge.badgeDescription,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .padding(4.dp)
                )
                Text(
                    text = badge.badgeDate,
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
                imageVector = if (badge.isFavorite)
                    Icons.Filled.Favorite
                else
                    Icons.Outlined.HeartBroken,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(24.dp)
                    .clickable { onUnLikeBadge(badge) }
            )
        }
    }
}