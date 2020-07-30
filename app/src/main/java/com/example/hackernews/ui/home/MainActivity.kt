package com.example.hackernews.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackernews.R
import com.example.hackernews.data.response.TopStory
import com.example.hackernews.ui.home.adapter.StoryAdapter
import com.example.hackernews.ui.home.presenter.MainPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity(), MainView.View {
    private val presenter: MainPresenter by inject()
    private lateinit var adapter: StoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        presenter.getStories()
        onAttachView()
        adapter = StoryAdapter()
        rv_stories.setHasFixedSize(true)
        rv_stories.layoutManager = LinearLayoutManager(this)
        rv_stories.adapter = adapter
    }

    override fun showStories(stories: MutableList<TopStory>) {
        adapter.setData(stories)
    }

    override fun showView() {
        progress_stories.visibility = View.GONE
        rv_stories.visibility = View.VISIBLE
        tv_favorites.visibility = View.VISIBLE
    }

    override fun hideView() {
        progress_stories.visibility = View.VISIBLE
        rv_stories.visibility = View.GONE
        tv_favorites.visibility = View.GONE
    }

    override fun showError(e: String?) {
        Toast.makeText(this, e,Toast.LENGTH_SHORT).show()
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDetachView() {
        presenter.onDetach()
    }
}