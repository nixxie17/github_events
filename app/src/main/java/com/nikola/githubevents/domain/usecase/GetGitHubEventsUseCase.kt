package com.nikola.githubevents.domain.usecase

import com.nikola.githubevents.domain.repository.GitHubEventsRepository

class GetGitHubEventsUseCase(private val repository: GitHubEventsRepository) {

    suspend operator fun invoke() = repository.getGitHubEvents()
}