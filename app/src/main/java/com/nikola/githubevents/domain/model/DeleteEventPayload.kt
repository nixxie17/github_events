package com.nikola.githubevents.domain.model

data class DeleteEventPayload(
    val ref: String?,
    val refType: String?,
    val pusherType: String?
) : EventPayload()
