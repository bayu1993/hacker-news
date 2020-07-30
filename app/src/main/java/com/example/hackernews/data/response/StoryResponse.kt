package com.example.hackernews.data.response

import com.google.gson.annotations.SerializedName

data class StoryResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("kids")
    val kids: MutableList<Int?>?,
    @SerializedName("score")
    val score: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String
)