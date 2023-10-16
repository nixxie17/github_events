package com.nikola.githubevents.domain.usecase.base

interface UseCaseResponse<Type> {
    fun onSuccess(result: Type)
    fun onError(error: UseCaseError?)
}

