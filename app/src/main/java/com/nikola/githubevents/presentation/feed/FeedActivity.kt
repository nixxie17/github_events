package com.nikola.githubevents.presentation.feed

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nikola.githubevents.R
import com.nikola.githubevents.databinding.ActivityFeedBinding
import com.nikola.githubevents.domain.model.GitHubEvent
import com.nikola.githubevents.presentation.details.DetailsActivity
import com.nikola.githubevents.presentation.feed.adapter.GitHubEventItemActionListener
import com.nikola.githubevents.presentation.feed.adapter.GitHubEventsFeedAdapter
import com.nikola.githubevents.utils.isNetworkAvailable
import com.squareup.moshi.Moshi
import org.koin.androidx.viewmodel.ext.android.viewModel


class FeedActivity: AppCompatActivity(), GitHubEventItemActionListener {

    private lateinit var binding: ActivityFeedBinding
    private var adapter: GitHubEventsFeedAdapter? = GitHubEventsFeedAdapter()
    private val feedViewModel: FeedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.swipeRefreshLayout.setOnRefreshListener(refreshListener);
        binding.feedRecyclerView.adapter = adapter
        adapter?.addOnActionListener(this)
        subscribeForDataChanges()
        getData()
    }

    private fun getData(){
        if (isNetworkAvailable()) {
            feedViewModel.getGitHubEvents()
        } else {
            Toast.makeText(
                this,
                getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun subscribeForDataChanges(){

        with(feedViewModel) {

            eventsData.observe(this@FeedActivity, Observer {
                binding.feedProgressBar.visibility = GONE
                adapter?.eventsList = it
                binding.swipeRefreshLayout.isRefreshing = false
            })

            showProgressBar.observe(this@FeedActivity, Observer { isVisible ->
                binding.feedProgressBar.visibility = if (isVisible) VISIBLE else GONE
            })

            error.observe(this@FeedActivity, Observer { err ->
                Toast.makeText(
                    this@FeedActivity,
                    err,
                    Toast.LENGTH_SHORT
                ).show()
                binding.swipeRefreshLayout.isRefreshing = false
            })
        }
    }

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        binding.swipeRefreshLayout.isRefreshing = true
        getData()
    }

    override fun onDestroy() {
        adapter = null
        super.onDestroy()
    }

    override fun onGitHubEventItemClick(gitHubEvent: GitHubEvent) {
        Intent(this@FeedActivity, DetailsActivity::class.java).apply {
            val data = feedViewModel.getJsonFromEventObject(gitHubEvent)
            putExtra(INTENT_GIT_HUB_EVENT_KEY, data)
            startActivity(this)
        }
    }

    companion object {
        const val INTENT_GIT_HUB_EVENT_KEY = "gitHubEventJsonKey"
    }
}
