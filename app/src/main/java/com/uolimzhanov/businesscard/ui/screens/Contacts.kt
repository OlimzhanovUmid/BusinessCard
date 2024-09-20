package com.uolimzhanov.businesscard.ui.screens

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
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

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
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
                Text(
                    text = stringResource(R.string.contacts)
                )
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
                        IconButton(
                            onClick = {
                                context.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("http://www.telegram.me/${user.telegram}")
                                    )
                                )
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
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = stringResource(R.string.email),
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                context.startActivity(
                                    Intent(
                                        Intent.ACTION_DIAL,
                                        Uri.parse("tel:${user.phoneNumber}")
                                    )
                                )
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
            ContactsScreen(user = user)
        }
    }
}
