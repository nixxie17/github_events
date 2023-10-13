package com.nikola.githubevents.data.repository

import com.nikola.githubevents.data.source.ApiService
import com.nikola.githubevents.domain.model.GitHubEvent
import com.nikola.githubevents.domain.repository.GitHubEventsRepository

class GitHubEventsRepositoryImpl(private val apiService: ApiService): GitHubEventsRepository {

    override suspend fun getGitHubEvents(): List<GitHubEvent> = apiService.getGitHubEvents().map{ eventDto -> eventDto.toDomain()}
}
