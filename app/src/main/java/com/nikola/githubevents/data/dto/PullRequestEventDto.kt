package com.nikola.githubevents.data.dto

import com.nikola.githubevents.data.mappers.toDomain
import com.nikola.githubevents.domain.model.GitHubEvent
import com.nikola.githubevents.domain.model.PullRequestEvent
import com.squareup.moshi.Json

data class PullRequestEventDto(
    override val id: String?,
    override val type: String?,
    override val actor: ActorDto?,
    override val repo: RepositoryDto?,
    @Json(name = "created_at") override val createdAt: String?,
    val payload: PullRequestEventPayloadDto
) : GitHubEventDto() {
    data class PullRequestEventPayloadDto(
        val action: String?,
        val number: Int,
        @Json(name = "pull_request")val pullRequest: PullRequestDto
    )

    data class PullRequestDto(
        val url: String?,
        val id: Long,
        @Json(name = "node_id")val nodeId: String?,
        @Json(name = "html_url")val htmlUrl: String?,
        @Json(name = "diff_url")val diffUrl: String?,
        @Json(name = "patch_url")val patchUrl: String?,
        @Json(name = "issue_url")val issueUrl: String?,
        val number: Int?,
        val state: String?,
        val locked: Boolean?,
        val title: String?,
        val user: UserDto?,
        val body: String?,
        @Json(name = "created_at")val createdAt: String?,
        @Json(name = "updated_at")val updatedAt: String?,
        @Json(name = "closed_at")val closedAt: String?,
        @Json(name = "merged_at")val mergedAt: String?,
        @Json(name = "merge_commit_sha")val mergeCommitSha: String?,
        val assignee: Any?,
        val assignees: List<Any>?,
        @Json(name = "requested_reviewers")val requestedReviewers: List<Any>?,
        @Json(name = "requested_teams")val requestedTeams: List<Any>?,
        val labels: List<Label>?,
        val milestone: Any?,
        val draft: Boolean?,
        @Json(name = "commits_url")val commitsUrl: String?,
        @Json(name = "review_comments_url")val reviewCommentsUrl: String?,
        @Json(name = "review_comment_url")val reviewCommentUrl: String?,
        @Json(name = "comments_url")val commentsUrl: String?,
        @Json(name = "statuses_url")val statusesUrl: String?,
        val head: Head?,
        val base: Base?,
        @Json(name = "_links")val links: Links?,
        @Json(name = "author_association")val authorAssociation: String?,
        val merged: Boolean?,
        @Json(name = "mergeable_state")val mergeableState: String?,
        @Json(name = "merged_by")val mergedBy: UserDto?,
        val comments: Int?,
        @Json(name = "review_comments")val reviewComments: Int?,
        @Json(name = "maintainer_can_modify")val maintainerCanModify: Boolean?,
        val commits: Int?,
        val additions: Int?,
        val deletions: Int?,
        @Json(name = "changed_files")val changedFiles: Int?
    )

    data class Head(
        val label: String?,
        val ref: String?,
        val sha: String?,
        val user: UserDto?
    )

    data class Base(
        val label: String?,
        val ref: String?,
        val sha: String?,
        val user: UserDto?
    )

    data class Links(
        val self: Self?,
        val html: Html?,
        val issue: Issue?,
        val comments: Comments?,
        @Json(name = "review_comments")val reviewComments: ReviewComments?,
        @Json(name = "review_comment")val reviewComment: ReviewComment?,
        val commits: Commits?,
        val statuses: Statuses?
    )

    data class Self(
        val href: String?
    )

    data class Html(
        val href: String?
    )

    data class Issue(
        val href: String?
    )

    data class Comments(
        val href: String?
    )

    data class ReviewComments(
        val href: String?
    )

    data class ReviewComment(
        val href: String?
    )

    data class Commits(
        val href: String?
    )

    data class Statuses(
        val href: String?
    )

    data class Label(
        val id: Long?,
        @Json(name = "node_id")val nodeId: String?,
        val url: String?,
        val name: String?,
        val color: String?,
        val default: Boolean?,
        val description: String?
    )

    override fun toDomain(): GitHubEvent = PullRequestEvent(
        id,
        type,
        actor?.toDomain(),
        repo?.toDomain(),
        PullRequestEvent.PullRequestEventPayload(
            payload.action,
            payload.number,
            PullRequestEvent.PullRequestEventPayload.PullRequest(
                payload.pullRequest.id,
                payload.pullRequest.url,
                payload.pullRequest.title,
                payload.pullRequest.user?.toDomain(),
                payload.pullRequest.commitsUrl,
                payload.pullRequest.reviewCommentsUrl,
                payload.pullRequest.reviewCommentUrl,
                payload.pullRequest.commentsUrl,
                payload.pullRequest.statusesUrl,
                payload.pullRequest.commits,
                payload.pullRequest.issueUrl,
            )
        ),
        createdAt
    )
}
