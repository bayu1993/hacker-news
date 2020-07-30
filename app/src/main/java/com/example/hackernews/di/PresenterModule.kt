package com.example.hackernews.di

import com.example.hackernews.ui.detail.presenter.DetailPresenter
import com.example.hackernews.ui.home.presenter.MainPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory { MainPresenter(get(), get()) }
    factory { DetailPresenter(get(), get()) }
}