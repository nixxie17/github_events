# github_events

Android application showcase demonstrating the usage of Clean Architecture, MVVM Architecture for the Presentation layer, Dependency Injection concept with the usage of Koin framework,
polling and mapping API data with Retrofit and the Moshi framework.

The project is using the free api from GitHub REST API

## Built With
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) and [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html#asynchronous-flow) - Official Kotlin's tooling for performing asynchronous work.
- [Android Jetpack](https://developer.android.com/jetpack) - Jetpack is a suite of libraries to help developers build state-of-the-art applications.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - The ViewModel is designed to store and manage UI-related data in a lifecycle conscious way..
- [Koin](https://insert-koin.io) - Koin is a dependency injection library for Android.
- [OkHttp](https://github.com/square/okhttp) - An HTTP client for making network calls.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Retrofit](https://github.com/square/retrofit) - A library for building REST API clients.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.

## Build-tool
You need to have [Android Studio Flamingo or above](https://developer.android.com/studio/preview) to build this project.

## Points of improvement
This is just a show case example which can be improved and extended:
* Schedule data update by using the Android's WorkManager framework for scheduling the execution of periodic tasks.
* Better error handling by creating Error classes and UI components for displaying of those errors.
* Add unit tests

## This show case is still a WIP
Although the code is already written for displaying GitHub event details screen the feature is still a WIP and not working due to an issue with the Moshi's library simmilar to this https://github.com/square/moshi/issues/874
which is out of the scope of this show case. Other approach of moving the data between the Android components is by using the standard Parcelable instead of the Moshi library or replacing Moshi with GSON.
