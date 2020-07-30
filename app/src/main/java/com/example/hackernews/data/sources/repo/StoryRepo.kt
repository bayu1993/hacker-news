package com.example.hackernews.data.sources.repo

import com.example.hackernews.data.response.CommentResponse
import com.example.hackernews.data.response.StoryResponse
import io.reactivex.Single

class StoryRepo(private val remoteDataSource: RemoteDataSource) : StoryDataSource {
    override fun getStories(): Single<MutableList<Int>> {
        return remoteDataSource.getStories()
    }

    override fun getComment(id: String): Single<CommentResponse> {
        return remoteDataSource.getComment(id)
    }

    override fun getStory(id: String): Single<StoryResponse> {
        return remoteDataSource.getStory(id)
    }
}