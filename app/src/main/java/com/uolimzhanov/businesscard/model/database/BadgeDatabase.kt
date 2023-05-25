package com.uolimzhanov.businesscard.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uolimzhanov.businesscard.model.dao.BadgeDao
import com.uolimzhanov.businesscard.model.entity.Badge
import com.uolimzhanov.businesscard.model.entity.LocalDateTimeConverter

@Database(
    entities = [Badge::class],
    version = 1
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class BadgeDatabase : RoomDatabase() {

    abstract val dao: BadgeDao

    companion object {
        const val DATABASE_NAME = "badges_db"
    }
}