package com.example.hackernews.network

import com.example.hackernews.data.response.CommentResponse
import com.example.hackernews.data.response.StoryResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET("topstories.json")
    fun getTopStories(): Single<MutableList<Int>>

    @GET("item/{id}")
    fun getItemComment(@Path("id") id: String): Single<CommentResponse>

    @GET("item/{id}")
    fun getItemStory(@Path("id") id: String): Single<StoryResponse>

}