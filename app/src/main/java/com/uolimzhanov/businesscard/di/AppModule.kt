package com.uolimzhanov.businesscard.di

import android.content.Context
import androidx.room.Room
import com.uolimzhanov.businesscard.model.database.BadgeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBadgeDatabase(@ApplicationContext context: Context): BadgeDatabase =
        Room.databaseBuilder(
            context,
            BadgeDatabase::class.java,
            BadgeDatabase.DATABASE_NAME
        ).createFromAsset("database/badges_db.db").build()
}