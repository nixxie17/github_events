package com.nikola.githubevents.presentation.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikola.githubevents.CREATE_EVENT
import com.nikola.githubevents.DELETE_EVENT
import com.nikola.githubevents.PULL_REQUEST_EVENT
import com.nikola.githubevents.PUSH_EVENT
import com.nikola.githubevents.UNKNOWN_EXCEPTION_MESSAGE
import com.nikola.githubevents.domain.model.GitHubEvent
import com.nikola.githubevents.domain.usecase.GetGitHubEventsUseCase
import com.nikola.githubevents.domain.usecase.base.UseCaseError
import com.nikola.githubevents.domain.usecase.base.UseCaseResponse
import com.squareup.moshi.Moshi

class FeedViewModel(
    private val gitHubEventsUseCase: GetGitHubEventsUseCase,
    private val moshi: Moshi
): ViewModel() {

    private val _eventsData = MutableLiveData<List<GitHubEvent>>()
    private val _showProgressBar = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()

    val eventsData: LiveData<List<GitHubEvent>> = _eventsData
    val showProgressBar: LiveData<Boolean> = _showProgressBar
    val error: LiveData<String> = _error

    fun getGitHubEvents(){
        _showProgressBar.value = true
        gitHubEventsUseCase.invoke(viewModelScope,null, object : UseCaseResponse<List<GitHubEvent>> {
                override fun onSuccess(result: List<GitHubEvent>) {
                    result.map { event ->  Log.d(TAG, "Event: $event")}
                    val githubEvents = result.filter {
                        it.type == CREATE_EVENT ||
                        it.type == DELETE_EVENT ||
                        it.type == PUSH_EVENT ||
                        it.type == PULL_REQUEST_EVENT
                    }
                    _eventsData.value = githubEvents
                    _showProgressBar.value = false
                }

                override fun onError(error: UseCaseError?) {
                    _showProgressBar.value = false
                    _error.value = error?.message ?: UNKNOWN_EXCEPTION_MESSAGE
                }
            }
        )
    }

    fun getJsonFromEventObject(gitHubEvent: GitHubEvent): String {
        val adapter = moshi.adapter(GitHubEvent::class.java)
        return adapter.toJson(gitHubEvent)
    }

    companion object {
        private val TAG = FeedViewModel::class.java.name
    }
}