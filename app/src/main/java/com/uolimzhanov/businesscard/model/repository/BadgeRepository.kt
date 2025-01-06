package com.uolimzhanov.businesscard.model.repository

import com.uolimzhanov.businesscard.model.database.BadgeDatabase
import com.uolimzhanov.businesscard.model.database.PrepopulateDb
import com.uolimzhanov.businesscard.model.entity.Badge
import kotlinx.coroutines.flow.Flow

class BadgeRepository(private val database: BadgeDatabase) {
    suspend fun upsertBadge(badge: Badge) {
        database.dao().upsertBadge(badge)
    }

    suspend fun deleteBadge(badge: Badge) {
        database.dao().deleteBadge(badge)
    }

    fun getBadgeById(id: Int): Badge? {
        return database.dao().getBadgeById(id)
    }

    fun getBadgesOrderedByName(): Flow<List<Badge>> {
        return database.dao().getBadgesOrderedByName()
    }

    fun getBadgesOrderedByNameAsc(): Flow<List<Badge>> {
        return database.dao().getBadgesOrderedByNameAsc()
    }

    fun getBadgesOrderedByDate(): Flow<List<Badge>> {
        return database.dao().getBadgesOrderedByDate()
    }

    fun getBadgesOrderedByDateAsc(): Flow<List<Badge>> {
        return database.dao().getBadgesOrderedByDateAsc()
    }

    suspend fun init() {
        PrepopulateDb.initialData.map {
            database.dao().upsertBadge(it)
        }
    }
}