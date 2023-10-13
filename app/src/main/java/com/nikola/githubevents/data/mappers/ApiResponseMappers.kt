package com.nikola.githubevents.data.mappers

import com.nikola.githubevents.data.dto.ActorDto
import com.nikola.githubevents.data.dto.RepositoryDto
import com.nikola.githubevents.data.dto.UserDto
import com.nikola.githubevents.domain.model.Actor
import com.nikola.githubevents.domain.model.Repository
import com.nikola.githubevents.domain.model.User

fun ActorDto.toDomain(): Actor = Actor(id, login, url, avatarUrl)

fun RepositoryDto.toDomain() = Repository(id, name, url)

fun UserDto.toDomain() = User(
    id,
    login,
    avatarUrl,
    url,
    followersUrl,
    followingUrl,
    reposUrl,
    eventsUrl
)
