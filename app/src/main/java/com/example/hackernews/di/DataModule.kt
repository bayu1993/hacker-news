package com.example.hackernews.di

import com.example.hackernews.data.sources.repo.RemoteDataSource
import com.example.hackernews.data.sources.repo.StoryRepo
import org.koin.dsl.module

val dataModule = module {
    single { RemoteDataSource(get()) }
    single { StoryRepo(get()) }
}