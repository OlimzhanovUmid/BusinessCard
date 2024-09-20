package com.uolimzhanov.businesscard.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.uolimzhanov.businesscard.R
import com.uolimzhanov.businesscard.extensions.dialNumber
import com.uolimzhanov.businesscard.extensions.openLink
import com.uolimzhanov.businesscard.extensions.openTelegram
import com.uolimzhanov.businesscard.extensions.sendEmails
import com.uolimzhanov.businesscard.model.entity.User
import com.uolimzhanov.businesscard.ui.theme.BusinessCardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier,
    user: User,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
    ) {
        LargeTopAppBar(
            scrollBehavior = scrollBehavior,
            title = {
                Text(stringResource(R.string.contacts))
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        )
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
                            context.openLink("https://g.dev/uolimzhanov")
                        }
                    )
                },
                trailingContent = {
                    Column(verticalArrangement = Arrangement.SpaceEvenly) {
                        IconButton(
                            onClick = {
                                context.openTelegram(user.telegram)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.telegram),
                                contentDescription = stringResource(R.string.telegram_account),
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                context.sendEmails(arrayOf(user.email))
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = stringResource(R.string.email),
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                context.dialNumber(user.phoneNumber)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Phone,
                                contentDescription = stringResource(R.string.phone),
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
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
            ContactsScreen(user = user)
        }
    }
}
