package com.nikola.githubevents.domain.model


data class User(
    val id: Int?,
    val login: String?,
    val avatarUrl: String?,
    val url: String?,
    val followersUrl: String?,
    val followingUrl: String?,
    val reposUrl: String?,
    val eventsUrl: String?,
)
