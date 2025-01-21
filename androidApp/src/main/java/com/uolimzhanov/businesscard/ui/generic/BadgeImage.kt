package com.uolimzhanov.businesscard.ui.generic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.SubcomposeAsyncImage
import com.uolimzhanov.businesscard.model.entity.Badge

/**
 * Created by uolimzhanov on 19.09.2024
 */
@Composable
fun BadgeImage(
    modifier: Modifier = Modifier,
    badge: Badge,
    contentScale: ContentScale = ContentScale.None
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = badge.badgeUrl,
        contentScale = contentScale,
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
    )
}