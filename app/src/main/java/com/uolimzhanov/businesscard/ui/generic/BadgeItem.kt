package com.uolimzhanov.businesscard.ui.generic

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uolimzhanov.businesscard.model.entity.Badge
import com.uolimzhanov.businesscard.ui.theme.BusinessCardTheme
import java.time.LocalDateTime

@Composable
fun BadgeItem(
    badge: Badge
) {
    Text(text = badge.badgeDate.toString())
}

@Preview(name = "Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BadgeItemPreview(){
    BusinessCardTheme {
        Surface {
            val badge = Badge(
                "",
                "Installed Studio",
                "InstalledStudio",
                LocalDateTime.parse("2012.12.03"),
                true
            )
            BadgeItem(badge = badge)
        }
    }
}