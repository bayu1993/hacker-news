package com.example.hackernews.ui.detail.adapter

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackernews.R
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    private val stories = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stories[position])
    }

    fun setData(data: MutableList<String>) {
        stories.clear()
        stories.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: String) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                itemView.tv_item_comment.text = Html.fromHtml(data, Html.FROM_HTML_MODE_COMPACT)
            else itemView.tv_item_comment.text = Html.fromHtml(data)

        }

    }
}