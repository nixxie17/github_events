package com.nikola.githubevents.presentation.feed.adapter

import com.nikola.githubevents.domain.model.GitHubEvent

interface GitHubEventItemActionListener {
    fun onGitHubEventItemClick(gitHubEvent: GitHubEvent)
}