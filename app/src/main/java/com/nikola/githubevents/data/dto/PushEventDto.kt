package com.nikola.githubevents.data.dto

import com.nikola.githubevents.data.mappers.toDomain
import com.nikola.githubevents.domain.model.GitHubEvent
import com.nikola.githubevents.domain.model.PushEventPayload
import com.squareup.moshi.Json

data class PushEventDto(
    override val id: String,
    override val type: String,
    override val actor: ActorDto,
    override val repo: RepositoryDto,
    override val createdAt: String,
    val payload: PushEventPayloadDto
) : GitHubEventDto() {
    data class PushEventPayloadDto(
        @Json(name = "push_id") val pushId: Long,
        val size: Int,
        @Json(name = "distinct_size") val distinctSize: Int,
        val ref: String,
        val head: String,
        val before: String,
        val commits: List<CommitDto>
    )

    data class CommitDto(
        val sha: String,
        val author: CommitterDto,
        val message: String,
        val distinct: Boolean,
        val url: String
    )

    data class CommitterDto(
        val name: String,
        val email: String
    )

    override fun toDomain()=
        GitHubEvent(
            id,
            type,
            actor.toDomain(),
            repo.toDomain(),
            PushEventPayload(payload.pushId, payload.size, payload.commits.map { commitDto ->
                PushEventPayload.Commit(
                    author = PushEventPayload.Committer(commitDto.author.name, commitDto.author.email),
                    message = commitDto.message,
                    url = commitDto.url
                )
            } ),
            createdAt
        )
}
