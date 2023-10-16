package com.nikola.githubevents.domain.usecase.base

data class UseCaseError(val message: String, val code: Int? = 0, val body: Any? = object {})
