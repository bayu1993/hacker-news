package com.example.hackernews.di

import com.example.hackernews.network.ApiClient
import org.koin.dsl.module

val networkModule = module {
    single { ApiClient().create() }
}