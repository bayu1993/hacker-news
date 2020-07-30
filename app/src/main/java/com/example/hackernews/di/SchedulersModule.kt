package com.example.hackernews.di

import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module

val schedulerModule = module {
    factory { CompositeDisposable() }
}