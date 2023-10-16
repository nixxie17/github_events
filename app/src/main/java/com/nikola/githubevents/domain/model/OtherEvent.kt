package com.nikola.githubevents.domain.model

import com.squareup.moshi.Json

data class OtherEvent (
    override val id: String?,
    @Json(name = "type")override val type: String?,
    override val actor: Actor?,
    override val repo: Repository?,
    override val createdAt: String?
): GitHubEvent()