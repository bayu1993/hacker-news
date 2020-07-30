package com.example.hackernews.ui.home.presenter

import com.example.hackernews.data.response.TopStory
import com.example.hackernews.data.sources.repo.StoryRepo
import com.example.hackernews.ui.home.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainPresenter(
    private val repo: StoryRepo,
    private val compositeDisposable: CompositeDisposable
) : MainView.Presenter {
    private var mView: MainView.View? = null
    private val list = mutableListOf<TopStory>()
    override fun getStories() {
        mView?.hideView()
        repo.getStories().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { ids ->
                    mView?.hideView()
                    for (i in 0 until ids.size) {
                        repo.getStory(ids[i].toString()).subscribeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io()).subscribeBy(
                                onSuccess = { story ->
                                    list.add(
                                        TopStory(
                                            story.id,
                                            story.title,
                                            story.kids?.count() ?: 0,
                                            story.score
                                        )
                                    )
                                    mView?.showView()
                                    mView?.showStories(list)
                                },
                                onError = {
                                    mView?.hideView()
                                    mView?.showError(it.localizedMessage)
                                }
                            )
                    }
                },
                onError = {
                    mView?.hideView()
                    mView?.showError(it.localizedMessage)
                }
            ).addTo(compositeDisposable)
    }

    override fun onAttach(view: MainView.View) {
        mView = view
    }

    override fun onDetach() {
        mView = null
        compositeDisposable.clear()
    }

}