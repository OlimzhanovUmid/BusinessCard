package com.uolimzhanov.businesscard.model.entity

import androidx.annotation.DrawableRes

data class User(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val telegram: String,
    @DrawableRes val profilePicId: Int
)
