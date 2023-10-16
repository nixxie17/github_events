package com.nikola.githubevents.di

import com.nikola.githubevents.CREATE_EVENT
import com.nikola.githubevents.DELETE_EVENT
import com.nikola.githubevents.OTHER_EVENT
import com.nikola.githubevents.PULL_REQUEST_EVENT
import com.nikola.githubevents.PUSH_EVENT
import com.nikola.githubevents.data.dto.CreateEventDto
import com.nikola.githubevents.data.dto.DeleteEventDto
import com.nikola.githubevents.data.dto.GitHubEventDto
import com.nikola.githubevents.data.dto.OtherEventDto
import com.nikola.githubevents.data.dto.PullRequestEventDto
import com.nikola.githubevents.data.dto.PushEventDto
import com.nikola.githubevents.domain.model.CreateEvent
import com.nikola.githubevents.domain.model.DeleteEvent
import com.nikola.githubevents.domain.model.GitHubEvent
import com.nikola.githubevents.domain.model.OtherEvent
import com.nikola.githubevents.domain.model.PullRequestEvent
import com.nikola.githubevents.domain.model.PushEvent
import com.nikola.githubevents.presentation.details.DetailsViewModel
import com.nikola.githubevents.presentation.feed.FeedViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DOMAIN_SCOPE = "Domain"

val AppModule = module {
    viewModel { FeedViewModel(get(), get(named(DOMAIN_SCOPE))) }
    viewModel { DetailsViewModel(get(named(DOMAIN_SCOPE))) }
    single { createGetPostsUseCase(get()) }
    single { createGitHubEventsRepository(get()) }
    single(named(DOMAIN_SCOPE)) { createDomainMoshiConfig()}
}

fun createDomainMoshiConfig(): Moshi =
    Moshi.Builder()
        .add(
            PolymorphicJsonAdapterFactory.of(GitHubEvent::class.java, "type")
                .withSubtype(DeleteEvent::class.java, DELETE_EVENT)
                .withSubtype(CreateEvent::class.java, CREATE_EVENT)
                .withSubtype(PullRequestEvent::class.java, PULL_REQUEST_EVENT)
                .withSubtype(PushEvent::class.java, PUSH_EVENT)
                .withDefaultValue(OtherEvent(null, OTHER_EVENT, null, null, null))
        )
        .addLast(KotlinJsonAdapterFactory())
        .build()
