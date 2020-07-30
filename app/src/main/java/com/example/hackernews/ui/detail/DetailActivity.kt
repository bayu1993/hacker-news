package com.example.hackernews.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackernews.R
import com.example.hackernews.data.response.StoryResponse
import com.example.hackernews.ui.detail.adapter.CommentAdapter
import com.example.hackernews.ui.detail.presenter.DetailPresenter
import com.example.hackernews.ui.home.MainActivity.Companion.EXTRA_ID
import com.example.hackernews.utils.SharePref
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity(), DetailView.View {
    private val presenter: DetailPresenter by inject()
    private lateinit var adapter: CommentAdapter
    private lateinit var sharePref: SharePref
    private var title: String = ""
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initView()
    }

    private fun initView() {
        supportActionBar?.title = "Detail Hacker News"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        adapter = CommentAdapter()
        sharePref = SharePref(this)
        rv_comment.setHasFixedSize(true)
        rv_comment.layoutManager = LinearLayoutManager(this)
        rv_comment.adapter = adapter
        if (id != 0) {
            presenter.getStory(id.toString())
        }
        onAttachView()
    }

    override fun showStory(data: StoryResponse) {
        title = data.title
        tv_favorites_detail.text = title
        tv_user.text = data.user
        tv_desc.text = data.type
        tv_date.visibility = View.GONE
        val titleSharePref = sharePref.getString(EXTRA_FAV)
        isFavorite = titleSharePref?.toLowerCase().equals(title.toLowerCase())
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        val item = menu?.findItem(R.menu.favorite_menu)
        if (isFavorite) item?.setIcon(R.drawable.ic_favorites) else item?.setIcon(R.drawable.ic_favorites_border)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_fav -> {
                if (!isFavorite) {
                    if (title.isNotEmpty()) {
                        item.setIcon(R.drawable.ic_favorites)
                        sharePref.setString(title, EXTRA_FAV)
                        Toast.makeText(this, "add to favorite", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    sharePref.clear()
                    item.setIcon(R.drawable.ic_favorites_border)
                    Toast.makeText(this, "delete from favorite", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_FAV = "EXTRA_FAV"
    }
}