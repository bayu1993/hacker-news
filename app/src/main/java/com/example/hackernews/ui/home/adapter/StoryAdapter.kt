package com.example.hackernews.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackernews.R
import com.example.hackernews.data.response.TopStory
import kotlinx.android.synthetic.main.item_story.view.*

class StoryAdapter : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    private val stories = mutableListOf<TopStory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stories[position])
    }

    fun setData(data: MutableList<TopStory>) {
        stories.clear()
        stories.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: TopStory) {
            itemView.tv_item_title.text = data.title
            itemView.tv_item_score.text = "Score ${data.score}"
            itemView.tv_comment_count.text = "Comment ${data.commentCount}"
        }

    }
}