package com.nikola.githubevents.domain.model

import com.squareup.moshi.Json


data class PullRequestEvent(
    override val id: String?,
    @Json(name = "type")override val type: String?,
    override val actor: Actor?,
    override val repo: Repository?,
    val payload: PullRequestEventPayload,
    override val createdAt: String?
): GitHubEvent(){
    data class PullRequestEventPayload (
        val action: String?,
        val number: Int?,
        val pullRequest: PullRequest?
    ) : EventPayload(){
        data class PullRequest(
            val id: Long?,
            val url: String?,
            val title: String?,
            val user: User?,
            val commitsUrl: String?,
            val reviewCommentsUrl: String?,
            val reviewCommentUrl: String?,
            val commentsUrl: String?,
            val statusesUrl: String?,
            val numberOfCommits: Int?,
            val issueUrl: String?
        )
    }
}

