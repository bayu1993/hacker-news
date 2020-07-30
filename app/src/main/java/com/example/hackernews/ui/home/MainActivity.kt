package com.example.hackernews.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackernews.R
import com.example.hackernews.data.response.TopStory
import com.example.hackernews.ui.detail.DetailActivity
import com.example.hackernews.ui.detail.DetailActivity.Companion.EXTRA_FAV
import com.example.hackernews.ui.home.adapter.StoryAdapter
import com.example.hackernews.ui.home.presenter.MainPresenter
import com.example.hackernews.utils.SharePref
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainView.View {
    private val presenter: MainPresenter by inject()
    private lateinit var adapter: StoryAdapter
    private lateinit var sharePref: SharePref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        sharePref = SharePref(this)
        presenter.getStories()
        onAttachView()
        adapter = StoryAdapter() {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(EXTRA_ID, it.id)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
        rv_stories.setHasFixedSize(true)
        rv_stories.layoutManager = LinearLayoutManager(this)
        rv_stories.adapter = adapter
        tv_favorites.text = sharePref.getString(EXTRA_FAV)
    }

    override fun onResume() {
        super.onResume()
        tv_favorites.text = sharePref.getString(EXTRA_FAV)
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
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show()
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDetachView() {
        presenter.onDetach()
    }

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
    }
}