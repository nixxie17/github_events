package com.nikola.githubevents.domain.model

data class CreateEvent(
    override val id: String?,
    override val type: String?,
    override val actor: Actor?,
    override val repo: Repository?,
    val payload: CreateEventPayload,
    override val createdAt: String?
): GitHubEvent() {
    data class CreateEventPayload(
        val ref: String?,
        val refType: String?,
        val masterBranch: String?,
        val description: String?,
        val pusherType: String?
    ) : EventPayload()
}