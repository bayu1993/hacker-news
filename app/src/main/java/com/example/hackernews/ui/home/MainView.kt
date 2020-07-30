package com.example.hackernews.ui.home

import com.example.hackernews.data.response.TopStory
import com.example.hackernews.ui.base.BaseContract

interface MainView {
    interface Presenter : BaseContract.Presenter<View>{
        fun getStories()
    }
    interface View : BaseContract.View{
        fun showStories(stories: MutableList<TopStory>)
        fun showView()
        fun hideView()
        fun showError(e:String?)
    }

}