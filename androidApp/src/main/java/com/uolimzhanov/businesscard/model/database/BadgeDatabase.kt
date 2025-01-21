package com.uolimzhanov.businesscard.model.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.uolimzhanov.businesscard.model.dao.BadgeDao
import com.uolimzhanov.businesscard.model.entity.Badge

@Database(
    entities = [Badge::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class BadgeDatabase : RoomDatabase() {

    abstract fun dao(): BadgeDao

    companion object {
        const val DATABASE_NAME = "badges_db"
    }
}

object PrepopulateDb {
    val initialData = listOf(
        Badge(
            id = 1,
            badgeUrl = "https://developers.google.com/static/profile/badges/profile/created-profile/created_profile.svg",
            badgeName = "Created Google Developer Profile",
            badgeNameRu = "Создан профиль разработчика Google",
            badgeDescription = "Created Google Developer Profile",
            badgeDescriptionRu = "Создан профиль разработчика Google",
            badgeDate = "12.06.2022",
            isFavorite = true
        ),
        Badge(
            id = 2,
            badgeUrl = "https://developers.google.com/static/profile/badges/playlists/first-playlist/badge.svg",
            badgeName = "First Learning Pathway and Quiz badge",
            badgeNameRu = "Первый учебный план и значок викторины",
            badgeDescription = "First Learning Pathway and Quiz badge",
            badgeDescriptionRu = "Пройден первый путь обучения и викторина",
            badgeDate = "12.06.2022",
            isFavorite = false
        ),
        Badge(
            id = 3,
            badgeUrl = "https://developers.google.com/static/profile/badges/playlists/android/android-basics-compose-unit-1-pathway-1/badge.svg",
            badgeName = "Introduction to programming in Kotlin",
            badgeNameRu = "Введение в программирование на Котлине",
            badgeDescription = "Introduction to programming in Kotlin",
            badgeDescriptionRu = "Пройден учебный курс и викторина «Введение в программирование на Kotlin».",
            badgeDate = "28.12.2022",
            isFavorite = false
        ),
        Badge(
            id = 4,
            badgeUrl = "https://developers.google.com/static/profile/badges/activity/android/sdk-platform-tools/badge.svg",
            badgeName = "Android SDK Platform Tools",
            badgeNameRu = "Инструменты платформы Android SDK",
            badgeDescription = "I used Android SDK Platform Tools",
            badgeDescriptionRu = "Инструменты платформы Android SDK",
            badgeDate = "08.05.2023",
            isFavorite = false
        ),
        Badge(
            id = 5,
            badgeUrl = "https://developers.google.com/static/profile/badges/playlists/android/android-basics-kotlin-pathway-two/android-basics-kotlin-pathway-two.svg",
            badgeName = "First App in Android Studio",
            badgeNameRu = "Первое приложение в Android Studio",
            badgeDescription = "First App in Android Studio",
            badgeDescriptionRu = "Первое приложение в Android Studio",
            badgeDate = "12.06.2022",
            isFavorite = true
        )
    )
}