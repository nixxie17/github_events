package com.nikola.githubevents.presentation.feed.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikola.githubevents.databinding.ItemEventBinding
import com.nikola.githubevents.domain.model.GitHubEvent
import com.nikola.githubevents.utils.parseDate
import kotlin.properties.Delegates

@SuppressLint("NotifyDataSetChanged")
class GitHubEventsFeedAdapter: RecyclerView.Adapter<GitHubEventsFeedAdapter.GitHubEventViewHolder>() {

    var eventsList: List<GitHubEvent> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    private var eventItemClickListener: GitHubEventItemActionListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubEventViewHolder {
        val binding = ItemEventBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return GitHubEventViewHolder(binding)
    }

    override fun getItemCount(): Int = if (eventsList.isEmpty()) 0 else eventsList.size

    override fun onBindViewHolder(holder: GitHubEventViewHolder, position: Int) {
        with(holder){
            with(eventsList[position]) {
                type.let {
                    binding.eventType.text = it
                }
                actor?.login?.let {
                    binding.login.text = it
                }
                repo?.name?.let {
                    binding.repository.text = it
                }

                createdAt?.let {
                    binding.createdAt.text = parseDate(it)
                }

                Glide.with(holder.itemView.context)
                    .load(actor?.avatarUrl)
                    .into(binding.profileImage)

                holder.itemView.setOnClickListener {
                    eventItemClickListener?.onGitHubEventItemClick(this)
                }
            }
        }
    }

    fun addOnActionListener(listener: GitHubEventItemActionListener){
        eventItemClickListener = listener
    }

    inner class GitHubEventViewHolder(val binding: ItemEventBinding)
        :RecyclerView.ViewHolder(binding.root)

}