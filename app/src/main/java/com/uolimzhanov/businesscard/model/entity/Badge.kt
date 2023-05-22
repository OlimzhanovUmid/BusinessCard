package com.uolimzhanov.businesscard.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Badge(
    val badgeUrl: String,
    val badgeDescription: String,
    val badgeDate: String,
    @PrimaryKey
    val id: Int? = 0
): Parcelable
