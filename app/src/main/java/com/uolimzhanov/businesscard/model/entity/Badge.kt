package com.uolimzhanov.businesscard.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Entity
@Serializable
@Parcelize
data class Badge(
    val badgeUrl: String,
    val badgeName: String,
    val badgeNameRu: String,
    val badgeDescription: String,
    val badgeDescriptionRu: String,
    val badgeDate: String,
    val isFavorite: Boolean,
    @PrimaryKey
    val id: Int
) : Parcelable