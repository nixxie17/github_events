package com.nikola.githubevents.domain.model

data class CreateEventPayload(
    val ref: String?,
    val refType: String?,
    val masterBranch: String?,
    val description: String?,
    val pusherType: String?
) : EventPayload()
