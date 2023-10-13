package com.nikola.githubevents.data.dto

import com.nikola.githubevents.domain.model.GitHubEvent
import com.squareup.moshi.Json

sealed class GitHubEventDto{
    abstract val id: String
    abstract val type: String
    abstract val actor: ActorDto
    abstract val repo: RepositoryDto
    @Json(name = "created_at") abstract val createdAt: String

    abstract fun toDomain(): GitHubEvent
}



