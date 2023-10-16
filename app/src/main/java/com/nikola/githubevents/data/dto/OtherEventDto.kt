package com.nikola.githubevents.data.dto

import com.nikola.githubevents.domain.model.GitHubEvent
import com.nikola.githubevents.domain.model.OtherEvent

data class OtherEventDto(
    override val id: String?,
    override val type: String?,
    override val actor: ActorDto?,
    override val repo: RepositoryDto?,
    override val createdAt: String?,
) : GitHubEventDto() {

    override fun toDomain(): GitHubEvent = OtherEvent(
        null,
        type,
        null,
        null,
        null,
    )
}
