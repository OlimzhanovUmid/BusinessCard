package com.uolimzhanov.businesscard.ui.screens

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uolimzhanov.businesscard.R
import com.uolimzhanov.businesscard.model.entity.User
import com.uolimzhanov.businesscard.ui.theme.BusinessCardTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactsScreen(
    user: User,
    modifier: Modifier,
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    var dropdownMenuState by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .padding(paddingValues = paddingValues)
    ) {
        ElevatedCard(
            shape = RoundedCornerShape(15),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            ListItem(
                leadingContent = {
                    Image(
                        painter = painterResource(id = user.profilePicId),
                        contentDescription = null,
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)

                    )
                },
                headlineContent = {
                    Text(
                        text = "${user.firstName} ${user.lastName}",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                supportingContent = {
                    Text(
                        text = "d.sixxx",
                        style = MaterialTheme.typography.bodyLarge,
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.clickable {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://g.dev/uolimzhanov")
                                )
                            )
                        }
                    )
                },
                trailingContent = {
                    Column(verticalArrangement = Arrangement.SpaceEvenly) {
                        Icon(
                            painter = painterResource(id = R.drawable.telegram),
                            contentDescription = stringResource(R.string.telegram_account),
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("http://www.telegram.me/${user.telegram}")
                                        )
                                    )
                                }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Icon(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = stringResource(R.string.email),
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent.createChooser(
                                            Intent().apply {
                                                action = Intent.ACTION_SEND
                                                selector = Intent(
                                                    Intent.ACTION_SENDTO,
                                                    Uri.parse("mailto:")
                                                )
                                                putExtra(Intent.EXTRA_EMAIL, arrayOf(user.email))
                                                putExtra(Intent.EXTRA_SUBJECT, user.email)
                                            },
                                            user.email
                                        )
                                    )
                                }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Icon(
                            imageVector = Icons.Outlined.Phone,
                            contentDescription = stringResource(R.string.phone),
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            Intent.ACTION_DIAL,
                                            Uri.parse("tel:${user.phoneNumber}")
                                        )
                                    )
                                }
                        )
                    }
                    DropdownMenu(
                        expanded = dropdownMenuState,
                        onDismissRequest = { dropdownMenuState = !dropdownMenuState }
                    ) {
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.telegram),
                                    contentDescription = stringResource(R.string.telegram_account),
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                            },
                            text = {
                                Text(text = stringResource(R.string.telegram_account))
                            }, onClick = {
                                context.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("http://www.telegram.me/${user.telegram}")
                                    )
                                )
                            }
                        )
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Email,
                                    contentDescription = stringResource(R.string.email),
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                            },
                            text = { Text(text = stringResource(R.string.email)) },
                            onClick = {
                                context.startActivity(
                                    Intent.createChooser(
                                        Intent().apply {
                                            action = Intent.ACTION_SEND
                                            selector = Intent(
                                                Intent.ACTION_SENDTO,
                                                Uri.parse("mailto:")
                                            )
                                            putExtra(Intent.EXTRA_EMAIL, arrayOf(user.email))
                                            putExtra(Intent.EXTRA_SUBJECT, user.email)
                                        },
                                        user.email
                                    )
                                )
                            }
                        )
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Phone,
                                    contentDescription = stringResource(R.string.phone),
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                            },
                            text = { Text(text = stringResource(R.string.phone)) },
                            onClick = {
                                context.startActivity(
                                    Intent(
                                        Intent.ACTION_DIAL,
                                        Uri.parse("tel:${user.phoneNumber}")
                                    )
                                )
                            }
                        )
                    }
                },
                modifier = Modifier.combinedClickable(
                    onClick = { },
                    onLongClick = { dropdownMenuState = !dropdownMenuState },
                )
            )
        }
    }
}

@Preview(name = "Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContactsPreview() {
    BusinessCardTheme {
        Surface {
            val user = User(
                firstName = "Umid",
                lastName = "Olimzhanov",
                phoneNumber = "+998931882441",
                email = "uolimzhanov@gmail.com",
                telegram = "https://t.me/uolimzhanov",
                profilePicId = R.drawable.profilepic
            )
            ContactsScreen(user = user, modifier = Modifier, paddingValues = PaddingValues(8.dp))
        }
    }
}
