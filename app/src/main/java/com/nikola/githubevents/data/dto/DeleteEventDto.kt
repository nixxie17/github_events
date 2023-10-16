package com.nikola.githubevents.data.dto

import com.nikola.githubevents.data.mappers.toDomain
import com.nikola.githubevents.domain.model.DeleteEvent
import com.nikola.githubevents.domain.model.GitHubEvent
import com.squareup.moshi.Json

data class DeleteEventDto(
    override val id: String?,
    override val type: String?,
    override val actor: ActorDto?,
    override val repo: RepositoryDto?,
    @Json(name = "created_at") override val createdAt: String?,
    val payload: DeleteEventPayloadDto?
) : GitHubEventDto(){

    data class DeleteEventPayloadDto(
        val ref: String?,
        @Json(name = "ref_type")val refType: String?,
        @Json(name = "pusher_type")val pusherType: String?
    )

    override fun toDomain()=
        DeleteEvent(
            id,
            type,
            actor?.toDomain(),
            repo?.toDomain(),
            DeleteEvent.DeleteEventPayload(
                payload?.ref,
                payload?.refType,
                payload?.pusherType
            ),
            createdAt
        )
}
