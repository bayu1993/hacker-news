package com.example.hackernews.data.sources.repo

import com.example.hackernews.network.ApiServices
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteDataSource(private val apiServices: ApiServices) {
    fun getTopStories(): Single<MutableList<Int>> {
        return apiServices.getTopStories().observeOn(AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()
        )
    }
}