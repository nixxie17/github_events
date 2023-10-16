package com.nikola.githubevents.domain.model

sealed class GitHubEvent{
    abstract val id: String?
    abstract val type: String?
    abstract val actor: Actor?
    abstract val repo: Repository?
    abstract val createdAt: String?
}



