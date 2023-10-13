package com.nikola.githubevents.domain.model

data class PushEventPayload(
    val pushId: Long,
    val size: Int?,
    val commits: List<Commit>?
) : EventPayload() {
    data class Commit(
        val author: Committer?,
        val message: String?,
        val url: String?
    )

    data class Committer(
        val name: String,
        val email: String?
    )
}

