package com.uolimzhanov.businesscard.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uolimzhanov.businesscard.model.dao.BadgeDao
import com.uolimzhanov.businesscard.model.entity.Badge

@Database(
    entities = [Badge::class],
    version = 1
)
abstract class BadgeDatabase : RoomDatabase() {

    abstract fun dao(): BadgeDao

    companion object {
        const val DATABASE_NAME = "badges_db"
    }
}