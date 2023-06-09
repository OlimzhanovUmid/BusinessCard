# Business Card

Business Card application using Compose and Hilt based on modern Android tech-stacks and MVVM architecture. This project is used to test newest available androidx libraries.

## Download

Go to the [Releases](https://github.com/OlimzhanovUmid/BusinessCard/releases/) to download the latest APK.

## Tech stack & Open-source libraries

- Minimum SDK level 25
- Material 3 design
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Hilt for dependency injection.
- JetPack
  - Compose - A modern toolkit for building native Android UI.
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct database.
  - App Startup - Provides a straightforward, performant way to initialize components at application startup.
- Architecture
  - MVVM Architecture (Declarative View - ViewModel - Model)
  - Repository pattern
- Material Design & Animations
- [Accompanist](https://github.com/google/accompanist) - A collection of extension libraries for Jetpack Compose.
- [Coil]([Coil (coil-kt.github.io)](https://coil-kt.github.io/coil/)) - Image loading.
- Room - For database.
- [Timber](https://github.com/JakeWharton/timber) - logging.
- Android 13 language selecting support.

## To Do

* Fetching badges data directly from [g.dev](https://developers.google.com/) site

* Prepopulating database with upserts instead of database file

* Proper image caching

* ...

## Localization

The project has only two localizations due to the database schema. In the future, it is planned to change the localization method.

## Licensing

The source code is licensed under GPL v3. License is available [here](/LICENSE).