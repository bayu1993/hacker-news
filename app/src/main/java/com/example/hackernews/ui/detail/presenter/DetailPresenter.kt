package com.example.hackernews.ui.detail.presenter

import com.example.hackernews.data.sources.repo.StoryRepo
import com.example.hackernews.ui.detail.DetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DetailPresenter(
    private val repo: StoryRepo,
    private val compositeDisposable: CompositeDisposable
) : DetailView.Presenter {
    private var mView: DetailView.View? = null

    override fun getStory(id: String) {
        mView?.hideView()
        repo.getStory(id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    mView?.showView()
                    mView?.showStory(it)
                    val comments = mutableListOf<String>()
                    if (it.kids != null) {
                        for (i in 0 until it.kids.size) {
                            repo.getComment(it.kids[i].toString())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io()).subscribeBy(
                                    onSuccess = { comment ->
                                        comments.add(comment.text)
                                        mView?.showComment(comments)
                                    }, onError = { error ->
                                        mView?.hideView()
                                        mView?.showError(error.localizedMessage)
                                    }
                                )
                        }
                    }
                },
                onError = {
                    mView?.hideView()
                    mView?.showError(it.localizedMessage)
                }
            ).addTo(compositeDisposable)
    }

    override fun onAttach(view: DetailView.View) {
        mView = view
    }

    override fun onDetach() {
        mView = null
        compositeDisposable.clear()
    }

}