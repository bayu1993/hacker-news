package com.example.hackernews.data.response

import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("parent")
    val parent: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("type")
    val type: String
)