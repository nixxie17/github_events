package com.nikola.githubevents.data.dto

import com.nikola.githubevents.data.mappers.toDomain
import com.nikola.githubevents.domain.model.DeleteEventPayload
import com.nikola.githubevents.domain.model.GitHubEvent
import com.squareup.moshi.Json

data class DeleteEventDto(
    override val id: String,
    override val type: String,
    override val actor: ActorDto,
    override val repo: RepositoryDto,
    override val createdAt: String,
    val payload: DeleteEventPayloadDto
) : GitHubEventDto(){

    data class DeleteEventPayloadDto(
        val ref: String,
        @Json(name = "ref_type")val refType: String,
        @Json(name = "pusher_type")val pusherType: String
    )

    override fun toDomain()=
        GitHubEvent(
            id,
            type,
            actor.toDomain(),
            repo.toDomain(),
            DeleteEventPayload(payload.ref, payload.refType, payload.pusherType),
            createdAt
        )
}
