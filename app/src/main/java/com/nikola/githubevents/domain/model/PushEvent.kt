package com.nikola.githubevents.domain.model


data class PushEvent(
    override val id: String?,
    override val type: String?,
    override val actor: Actor?,
    override val repo: Repository?,
    val payload: PushEventPayload,
    override val createdAt: String?
): GitHubEvent() {
    data class PushEventPayload(
        val pushId: Long?,
        val size: Int?,
        val commits: List<Commit>?
    ) : EventPayload() {
        data class Commit(
            val author: Committer?,
            val message: String?,
            val url: String?
        )

        data class Committer(
            val name: String?,
            val email: String?
        )
    }
}


