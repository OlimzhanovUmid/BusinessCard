package com.uolimzhanov.businesscard.extensions

import android.content.Context
import android.content.Intent
import android.content.Intent.createChooser
import android.net.Uri

/**
 * Created by uolimzhanov on 20.09.2024
 */
fun Context.sendEmails(
    emails: Array<String>,
    subject: String? = null
) = startActivity(
    createChooser(
        Intent().apply {
            action = Intent.ACTION_SEND
            selector = Intent(
                Intent.ACTION_SENDTO,
                Uri.parse("mailto:")
            )
            putExtra(Intent.EXTRA_EMAIL, emails)
            subject?.let {
                putExtra(Intent.EXTRA_SUBJECT, it)
            }
        },
        null
    )
)

fun Context.openTelegram(
    telegram: String
) = startActivity(
    Intent(
        Intent.ACTION_VIEW,
        Uri.parse("tg://resolve?domain=${telegram}")
    )
)

fun Context.dialNumber(phoneNumber: String) = startActivity(
    Intent(
        Intent.ACTION_DIAL,
        Uri.parse("tel:${phoneNumber}")
    )
)

fun Context.openLink(link: String) = startActivity(
    Intent(
        Intent.ACTION_VIEW,
        Uri.parse(link)
    )
)