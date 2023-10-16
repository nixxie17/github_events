package com.nikola.githubevents.domain.usecase

import com.nikola.githubevents.domain.model.GitHubEvent
import com.nikola.githubevents.domain.repository.GitHubEventsRepository
import com.nikola.githubevents.domain.usecase.base.UseCase

class GetGitHubEventsUseCase(private val repository: GitHubEventsRepository): UseCase<List<GitHubEvent>, Any?>() {

    override suspend fun run(params: Any?): List<GitHubEvent> {
        return repository.getGitHubEvents()
    }
}