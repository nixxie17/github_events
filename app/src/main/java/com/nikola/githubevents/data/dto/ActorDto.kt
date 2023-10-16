package com.nikola.githubevents.data.dto

import com.squareup.moshi.Json

data class ActorDto(
    @Json(name = "id") val id: Long?,
    @Json(name = "login") val login: String?,
    @Json(name = "display_login") val displayLogin: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "gravatar_id") val gravatarId: String?,
    @Json(name = "avatar_url") val avatarUrl: String?
)
