pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    val versionAgp: String by settings
    val versionKotlin: String by settings
    val versionDagger: String by settings
    val versionKsp: String by settings

    plugins {
        id("com.android.application") version versionAgp apply false
        id("org.jetbrains.kotlin.android") version versionKotlin apply false
        id("org.jetbrains.kotlin.kapt") version versionKotlin apply false
        id("com.google.dagger.hilt.android") version versionDagger apply false
        id("com.google.devtools.ksp") version versionKsp apply false
        id("org.jetbrains.kotlin.plugin.parcelize") version versionKotlin apply false
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://www.jitpack.io")
    }
}
rootProject.name = "BusinessCard"
include(":app")
