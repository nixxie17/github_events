package com.nikola.githubevents.data.source

import com.nikola.githubevents.data.dto.GitHubEventDto
import retrofit2.http.GET

interface ApiService {

    @GET("/events")
    suspend fun getGitHubEvents(): List<GitHubEventDto>
}