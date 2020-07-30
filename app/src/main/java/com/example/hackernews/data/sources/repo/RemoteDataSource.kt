package com.example.hackernews.data.sources.repo

import com.example.hackernews.data.response.CommentResponse
import com.example.hackernews.data.response.StoryResponse
import com.example.hackernews.data.response.TopStory
import com.example.hackernews.network.ApiServices
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteDataSource(private val apiServices: ApiServices) {
    fun getStories(): Single<MutableList<Int>> {
        return apiServices.getTopStories().observeOn(AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()
        )
    }

    fun getComment(id: String): Single<CommentResponse> {
        return apiServices.getItemComment("$id.json").observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getStory(id: String): Single<StoryResponse> {
        return apiServices.getItemStory("$id.json").observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}