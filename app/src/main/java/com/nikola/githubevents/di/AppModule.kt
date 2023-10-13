package com.nikola.githubevents.di

import com.nikola.githubevents.presentation.feed.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    viewModel { FeedViewModel(get()) }
    single { createGetPostsUseCase(get()) }
    single { createGitHubEventsRepository(get()) }
}