package com.nikola.githubevents

import android.app.Application
import com.nikola.githubevents.di.AppModule
import com.nikola.githubevents.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GitHubEventsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@GitHubEventsApplication)
            modules(listOf(AppModule, NetworkModule))
        }

    }
}
