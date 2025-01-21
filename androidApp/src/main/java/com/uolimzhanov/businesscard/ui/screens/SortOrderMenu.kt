package com.uolimzhanov.businesscard.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.uolimzhanov.businesscard.R
import com.uolimzhanov.businesscard.model.entity.SortOrder

@Composable
fun SortOrderMenu(
    isExpanded: Boolean = true,
    selectedOrder: SortOrder = SortOrder.NAME,
    onDismiss: () -> Unit = {},
    onItemSelected: (SortOrder) -> Unit = {}
) {
    DropdownMenu(
        expanded = isExpanded,
        offset = DpOffset(x = 0.dp, y = (-64).dp),
        onDismissRequest = onDismiss
    ) {
        Text(
            text = stringResource(R.string.sort_order),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        SortOrder.entries.forEach {
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        painter = painterResource(
                            id = if (it.ordinal % 2 == 0) R.drawable.arrow_up_thin else R.drawable.arrow_down_thin
                        ),
                        contentDescription = null
                    )
                },
                text = {
                    Text(text = stringResource(id = it.textId))
                },
                onClick = {
                    onItemSelected(it)
                    onDismiss()
                },
                trailingIcon = {
                    RadioButton(
                        selected = selectedOrder == it,
                        onClick = {
                            onItemSelected(it)
                            onDismiss()
                        }
                    )
                }
            )
        }
    }
}