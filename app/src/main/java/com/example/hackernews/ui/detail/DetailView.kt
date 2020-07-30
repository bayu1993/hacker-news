package com.example.hackernews.ui.detail

import com.example.hackernews.data.response.StoryResponse
import com.example.hackernews.ui.base.BaseContract

interface DetailView {
    interface Presenter : BaseContract.Presenter<View> {
        fun getStory(id: String)
    }

    interface View : BaseContract.View {
        fun showStory(data: StoryResponse)
        fun showError(e: String?)
        fun hideView()
        fun showView()
        fun showComment(comments: MutableList<String>?)
    }
}