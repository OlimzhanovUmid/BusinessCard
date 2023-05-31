package com.uolimzhanov.businesscard.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
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
    val id: Int? = 0
): Parcelable

/*
class LocalDateTimeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?):Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

}*/
