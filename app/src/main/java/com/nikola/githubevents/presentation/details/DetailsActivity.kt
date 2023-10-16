package com.nikola.githubevents.presentation.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.nikola.githubevents.NO_DETAILS_AVAILABLE
import com.nikola.githubevents.databinding.ActivityDetailsBinding
import com.nikola.githubevents.presentation.feed.FeedActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val detailsViewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        subscribeForDataChanges()
        init()
    }

    private fun init(){
        val data = intent.extras?.getString(FeedActivity.INTENT_GIT_HUB_EVENT_KEY)
        if(!data.isNullOrEmpty()){
            detailsViewModel.setUpDetailsData(data)
        } else {
            Toast.makeText(
                this@DetailsActivity,
                NO_DETAILS_AVAILABLE,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun subscribeForDataChanges(){
        with(detailsViewModel) {
            detailsData.observe(this@DetailsActivity, Observer { data ->
                data.avatarUrl?.let {
                    Glide.with(this@DetailsActivity)
                    .load(it)
                    .into(binding.profileImage)
                }

                data.login?.let {
                    binding.login.text = it
                }

                val createdAt = buildString {
                    data.type?.let {
                        append("Event of type $it ")
                    }
                    data.createdAt?.let {
                        append(" on $it")
                    }
                }
                binding.createdAt.text = createdAt
                binding.details.text = data.details
            })
        }
    }
}