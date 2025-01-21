package com.uolimzhanov.businesscard.di

import androidx.room.Room
import com.uolimzhanov.businesscard.model.database.BadgeDatabase
import com.uolimzhanov.businesscard.model.repository.BadgeRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val modelModule = module {
    single<BadgeDatabase> {
        Room.databaseBuilder(
            androidContext(),
            BadgeDatabase::class.java,
            BadgeDatabase.DATABASE_NAME
        ).build()
    }
    singleOf(::BadgeRepository)
}
