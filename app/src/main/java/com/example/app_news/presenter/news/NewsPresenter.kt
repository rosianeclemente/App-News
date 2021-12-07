package com.example.app_news.presenter.news

import androidx.recyclerview.widget.RecyclerView
import com.example.app_news.model.NewsResponse
import com.example.app_news.model.data.NewsDataSource
import com.example.app_news.presenter.ViewHome

class NewsPresenter(
    val view: ViewHome.View,
    private val dataSource: NewsDataSource
) : NewsHome.Presenter {
    override fun requestAll() {
        this.view.showProgressBar()
        this.dataSource.getBreakingNews(this)
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        this.view.showArticles(newsResponse.articles)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressbar()
    }
}