package com.nikola.githubevents.data.dto

import com.nikola.githubevents.data.mappers.toDomain
import com.nikola.githubevents.domain.model.CreateEvent
import com.squareup.moshi.Json

data class CreateEventDto(
    override val id: String?,
    override val type: String?,
    override val actor: ActorDto?,
    override val repo: RepositoryDto?,
    @Json(name = "created_at") override val createdAt: String?,
    val payload: CreateEventPayloadDto?
) : GitHubEventDto(){

    data class CreateEventPayloadDto(
        val ref: String?,
        @Json(name = "ref_type")val refType: String?,
        @Json(name = "master_branch")val masterBranch: String?,
        val description: String?,
        @Json(name = "pusher_type")val pusherType: String?
    )

    override fun toDomain()=
        CreateEvent(
            id,
            type,
            actor?.toDomain(),
            repo?.toDomain(),
            CreateEvent.CreateEventPayload(
                payload?.ref,
                payload?.refType,
                payload?.masterBranch,
                payload?.description,
                payload?.pusherType
            ),
            createdAt
        )
}
