package com.uolimzhanov.businesscard.model.repository

import com.uolimzhanov.businesscard.R
import com.uolimzhanov.businesscard.model.entity.User

object UserRepository {
    val user = User(
        firstName = "Umid",
        lastName = "Olimzhanov",
        phoneNumber = "+998931882441",
        email = "uolimzhanov@gmail.com",
        telegram = "uolimzhanov",
        profilePicId = R.drawable.profilepic
    )
}