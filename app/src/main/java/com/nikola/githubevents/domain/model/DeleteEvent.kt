package com.nikola.githubevents.domain.model

import com.squareup.moshi.Json


data class DeleteEvent(
    override val id: String?,
    @Json(name = "type")override val type: String?,
    override val actor: Actor?,
    override val repo: Repository?,
    val payload: DeleteEventPayload,
    override val createdAt: String?
): GitHubEvent() {
    data class DeleteEventPayload(
        val ref: String?,
        val refType: String?,
        val pusherType: String?
    ) : EventPayload()
}

