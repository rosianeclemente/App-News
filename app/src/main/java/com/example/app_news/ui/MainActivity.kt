package com.example.app_news.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_news.R
import com.example.app_news.adapter.MainAdapter
import com.example.app_news.model.Article
import com.example.app_news.model.data.NewsDataSource
import com.example.app_news.presenter.ViewHome
import com.example.app_news.presenter.news.NewsPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbstractActivity() , ViewHome.View{
    private val mainAdapter by lazy{
        MainAdapter()
    }
    private lateinit var presenter: NewsPresenter

    override fun getLayout(): Int = R.layout.activity_main

    override fun onInject() {
        val dataSource = NewsDataSource()
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
        configRecycle()

    }
    private fun configRecycle(){
        with(rvNews){
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity, DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun showProgressBar() {
        rvProgressBar.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressbar() {
        rvProgressBar.visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }

}