package com.nikola.githubevents.domain.repository

import com.nikola.githubevents.domain.model.GitHubEvent

interface GitHubEventsRepository {

    suspend fun getGitHubEvents(): List<GitHubEvent>
}