package com.example.app_news.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_news.R
import com.example.app_news.adapter.MainAdapter
import com.example.app_news.model.Article
import com.example.app_news.model.data.NewsDataSource
import com.example.app_news.presenter.ViewHome
import com.example.app_news.presenter.search.SearchPresenter
import com.example.app_news.utils.UtilQueryTextListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AbstractActivity(), ViewHome.View{
    private val mainAdapter by lazy{
        MainAdapter()
    }
    private lateinit var  presenter: SearchPresenter
    override fun getLayout(): Int = R.layout.activity_search

    override fun onInject() {
        val dataSource = NewsDataSource()
        presenter = SearchPresenter(this, dataSource)
        configRecycle()
        search()
    }

    private fun search(){
        searchNews.setOnQueryTextListener(
            UtilQueryTextListener(
                this.lifecycle
            ){
                newText ->
                newText?.let{ query ->
                    if (query.isNotEmpty()){
                        presenter.search(query)
                        rvProgressBarSearch.visibility = View.VISIBLE
                    }
                }
            }
        )
    }
    private fun configRecycle(){
        with(rvSearch){
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@SearchActivity, DividerItemDecoration.VERTICAL
                )
            )
        }
    }
    override fun showProgressBar() {
       rvProgressBarSearch.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressbar() {
        rvProgressBarSearch.visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }

}