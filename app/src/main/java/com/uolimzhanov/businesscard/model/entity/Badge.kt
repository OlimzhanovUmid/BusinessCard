package com.uolimzhanov.businesscard.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Entity
@Parcelize
data class Badge(
    val badgeUrl: String,
    val badgeName: String,
    val badgeDescription: String,
    val badgeDate: LocalDateTime,
    val isFavorite: Boolean,
    @PrimaryKey
    val id: Int? = 0
): Parcelable

class LocalDateTimeConverter {
    @TypeConverter
    fun timeToString(time: LocalDateTime): String {
        return time.toString()
    }

    @TypeConverter
    fun stringToTime(string: String): LocalDateTime {
        return LocalDateTime.parse(string)
    }
}