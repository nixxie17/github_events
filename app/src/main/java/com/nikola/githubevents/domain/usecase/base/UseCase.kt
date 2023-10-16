package com.nikola.githubevents.domain.usecase.base

import com.nikola.githubevents.CANCELLATION_EXCEPTION_MESSAGE
import com.nikola.githubevents.UNKNOWN_EXCEPTION_MESSAGE
import kotlinx.coroutines.*
import java.util.concurrent.CancellationException

abstract class UseCase<Type, in Params>() where Type : Any {

    abstract suspend fun run(params: Params? = null): Type


    fun invoke(scope: CoroutineScope, params: Params?, onResult: UseCaseResponse<Type>?) {

        scope.launch {
            try {
                val result = run(params)
                onResult?.onSuccess(result)
            } catch (e: CancellationException) {
                e.printStackTrace()
                onResult?.onError(UseCaseError(e.message ?: CANCELLATION_EXCEPTION_MESSAGE))
            } catch (e: Exception) {
                e.printStackTrace()
                onResult?.onError(UseCaseError(e.message ?: UNKNOWN_EXCEPTION_MESSAGE))
            }
        }
    }

}