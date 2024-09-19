package com.uolimzhanov.businesscard.ui.generic

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.uolimzhanov.businesscard.R
import com.uolimzhanov.businesscard.model.entity.Badge

/**
 * Created by uolimzhanov on 19.09.2024
 */
@Composable
fun BadgeDeletionDialog(
    badge: Badge,
    onConfirm: (Badge) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(imageVector = Icons.Default.Warning, contentDescription = null)
        },
        title = {
            Text(stringResource(R.string.delete_badge))
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(badge) }
            ) {
                Text(stringResource(R.string.yes))
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.no))
            }
        }
    )
}