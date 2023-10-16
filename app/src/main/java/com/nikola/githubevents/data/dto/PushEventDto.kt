package com.nikola.githubevents.data.dto

import com.nikola.githubevents.data.mappers.toDomain
import com.nikola.githubevents.domain.model.PushEvent
import com.squareup.moshi.Json

data class PushEventDto(
    override val id: String?,
    override val type: String?,
    override val actor: ActorDto?,
    override val repo: RepositoryDto?,
    @Json(name = "created_at") override val createdAt: String?,
    val payload: PushEventPayloadDto?
) : GitHubEventDto() {
    data class PushEventPayloadDto(
        @Json(name = "push_id") val pushId: Long?,
        val size: Int?,
        @Json(name = "distinct_size") val distinctSize: Int?,
        val ref: String?,
        val head: String?,
        val before: String?,
        val commits: List<CommitDto>?
    )

    data class CommitDto(
        val sha: String?,
        val author: CommitterDto?,
        val message: String?,
        val distinct: Boolean?,
        val url: String?
    )

    data class CommitterDto(
        val name: String?,
        val email: String?
    )

    override fun toDomain()=
        PushEvent(
            id,
            type,
            actor?.toDomain(),
            repo?.toDomain(),
            PushEvent.PushEventPayload(payload?.pushId, payload?.size,
                payload?.commits?.map { commitDto ->
                    PushEvent.PushEventPayload.Commit(
                        author = PushEvent.PushEventPayload.Committer(
                            commitDto.author?.name,
                            commitDto.author?.email
                        ),
                        message = commitDto.message,
                        url = commitDto.url
                    )
                }
            ),
            createdAt
        )
}
