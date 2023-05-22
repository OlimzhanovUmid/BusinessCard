package com.uolimzhanov.businesscard.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.uolimzhanov.businesscard.model.entity.Badge
import kotlinx.coroutines.flow.Flow

@Dao
interface BadgeDao {
    @Upsert
    suspend fun upsertBadge(badge: Badge)

    @Delete
    suspend fun deleteBadge(badge: Badge)

    @Query("SELECT * FROM Badge WHERE id = :id")
    fun getBadgeById(id: Int) : Badge?

    @Query("SELECT * FROM Badge")
    fun getCBadgeOrdered() : Flow<List<Badge>>
}