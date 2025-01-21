package com.uolimzhanov.businesscard.ui.generic

import android.content.res.Configuration
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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uolimzhanov.businesscard.model.BadgeEvent
import com.uolimzhanov.businesscard.model.entity.Badge
import com.uolimzhanov.businesscard.ui.theme.BusinessCardTheme
import java.util.Locale

@Composable
fun BadgeItem(
    badge: Badge,
    modifier: Modifier = Modifier,
    onEvent: (BadgeEvent) -> Unit
) {
    val isRussian = Locale.getDefault().language == "ru"
    Card(
        modifier = modifier.aspectRatio(0.66f)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                BadgeImage(
                    badge = badge,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(6f)
                )
                Text(
                    text = if (isRussian) badge.badgeNameRu else badge.badgeName,
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
                imageVector =
                if (badge.isFavorite)
                    Icons.Filled.Favorite
                else
                    Icons.Outlined.HeartBroken,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(24.dp)
                    .clickable { onEvent(BadgeEvent.UnLikeBadge(badge)) }
            )
        }
    }
}

@Preview(name = "Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BadgeItemPreview() {
    BusinessCardTheme {
        Surface {
            val badge = Badge(
                "",
                "Installed Studio",
                "Установил Студию",
                "Installed Android Studio",
                "Установил Андроид студио",
                "31.01.2021",
                true,
                1
            )
            BadgeItem(
                badge = badge, onEvent = {}
            )
        }
    }
}