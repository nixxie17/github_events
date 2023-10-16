package com.nikola.githubevents.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikola.githubevents.CREATE_EVENT
import com.nikola.githubevents.DELETE_EVENT
import com.nikola.githubevents.NO_DETAILS_AVAILABLE
import com.nikola.githubevents.PULL_REQUEST_EVENT
import com.nikola.githubevents.PUSH_EVENT
import com.nikola.githubevents.domain.model.CreateEvent
import com.nikola.githubevents.domain.model.DeleteEvent
import com.nikola.githubevents.domain.model.GitHubEvent
import com.nikola.githubevents.domain.model.PullRequestEvent
import com.nikola.githubevents.domain.model.PushEvent
import com.squareup.moshi.Moshi

class DetailsViewModel(private val moshi: Moshi): ViewModel() {

    data class GitHubEventDetails(
        val avatarUrl: String?,
        val login: String?,
        val type: String?,
        val createdAt: String?,
        val details: String
    )

    private val _detailsData = MutableLiveData<GitHubEventDetails>()
    val detailsData: LiveData<GitHubEventDetails> = _detailsData

    fun setUpDetailsData(json: String) {
        val jsonAdapter = moshi.adapter(GitHubEvent::class.java)
        val event = jsonAdapter.fromJson(json)

        val details = when(event?.type){
            PUSH_EVENT -> getPushEventDetails(event as PushEvent)
            CREATE_EVENT -> getCreateEventDetails(event as CreateEvent)
            DELETE_EVENT -> getDeleteEventDetails(event as DeleteEvent)
            PULL_REQUEST_EVENT -> getPullRequestEventDetails(event as PullRequestEvent)
            else -> NO_DETAILS_AVAILABLE
        }

        val data = GitHubEventDetails(
            event?.actor?.avatarUrl,
            event?.actor?.login,
            event?.type,
            event?.createdAt,
            details
        )
        _detailsData.value = data
    }

    private fun getPushEventDetails(event: PushEvent): String {
            val payload = event.payload
            return buildString {
                append("Number of commits: ${payload.size} \n")
                append("Commits: \n\n")
                payload.commits?.forEach { commit -> append("Author: ${commit.author?.name}\n " +
                        "Message: ${commit.message}\n " +
                        "Url: ${commit.url} \n\n")
                }
        }
    }

    private fun getPullRequestEventDetails(event: PullRequestEvent): String {
        val payload = event.payload
            return buildString {
                append("Opened by: ${payload.pullRequest?.user?.login} \n")
                append("Action: ${payload.action} \n")
                append("Number of commits: ${payload.pullRequest?.numberOfCommits} \n")
                append("Pull request url: ${payload.pullRequest?.url} \n")
            }
        }
    }

    private fun getCreateEventDetails(event: CreateEvent): String{
        val payload = event.payload
        return buildString{
            append("Ref: ${payload.ref} \n")
            append("Type: ${payload.refType} \n")
            append("Master branch: ${payload.masterBranch} \n")
            payload.description?.let {
                append("Description: ${payload.description} \n")
            }
        }
    }

    private fun getDeleteEventDetails(event: DeleteEvent): String{
        val payload = event.payload
        return buildString{
            append("Ref: ${payload.ref} \n")
            append("Type: ${payload.refType} \n")
            append("Pusher type: ${payload.pusherType} \n")
        }
    }