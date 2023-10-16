package com.nikola.githubevents

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.nikola.githubevents.di.AppModule
import com.nikola.githubevents.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GitHubEventsApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)


        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@GitHubEventsApplication)
            modules(listOf(AppModule, NetworkModule))
        }
    }
}
