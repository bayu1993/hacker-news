package com.example.hackernews.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackernews.R
import com.example.hackernews.data.response.StoryResponse
import com.example.hackernews.ui.detail.adapter.CommentAdapter
import com.example.hackernews.ui.detail.presenter.DetailPresenter
import com.example.hackernews.ui.home.MainActivity.Companion.EXTRA_ID
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity(), DetailView.View {
    private val presenter: DetailPresenter by inject()
    private lateinit var adapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initView()
    }

    private fun initView() {
        val id = intent.getIntExtra(EXTRA_ID, 0)
        adapter = CommentAdapter()
        rv_comment.setHasFixedSize(true)
        rv_comment.layoutManager = LinearLayoutManager(this)
        rv_comment.adapter = adapter
        if (id != 0) {
            presenter.getStory(id.toString())
        }
        onAttachView()
    }

    override fun showStory(data: StoryResponse) {
        tv_favorites_detail.text = data.title
        tv_user.text = data.user
        tv_desc.text = data.type
        tv_date.visibility = View.GONE
    }

    override fun showError(e: String?) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show()
    }

    override fun hideView() {
        tv_favorites_detail.visibility = View.GONE
        tv_user.visibility = View.GONE
        tv_desc_title.visibility = View.GONE
        tv_desc.visibility = View.GONE
        tv_date.visibility = View.GONE
        tv_comment_title.visibility = View.GONE
        rv_comment.visibility = View.GONE
        progress_detail.visibility = View.VISIBLE
    }

    override fun showView() {
        tv_favorites_detail.visibility = View.VISIBLE
        tv_user.visibility = View.VISIBLE
        tv_desc_title.visibility = View.VISIBLE
        tv_desc.visibility = View.VISIBLE
        tv_date.visibility = View.VISIBLE
        tv_comment_title.visibility = View.VISIBLE
        rv_comment.visibility = View.VISIBLE
        progress_detail.visibility = View.GONE
    }

    override fun showComment(comments: MutableList<String>?) {
        comments?.let {
            adapter.setData(it)
        }
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDetachView() {
        presenter.onDetach()
    }
}