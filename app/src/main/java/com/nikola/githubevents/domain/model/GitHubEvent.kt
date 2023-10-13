package com.nikola.githubevents.domain.model

data class GitHubEvent(
    val id: String,
    val type: String,
    val actor: Actor,
    val repo: Repository?,
    val payload: EventPayload?,
    val createdAt: String
)
