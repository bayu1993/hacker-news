package com.example.hackernews.data.sources.repo

import com.example.hackernews.data.response.CommentResponse
import com.example.hackernews.data.response.StoryResponse
import io.reactivex.Single

interface StoryDataSource {
    fun getStories(): Single<MutableList<Int>>
    fun getComment(id:String): Single<CommentResponse>
    fun getStory(id:String): Single<StoryResponse>
}