package com.example.app_news.presenter.favorite

import com.example.app_news.model.Article
import com.example.app_news.model.data.NewsDataSource
import com.example.app_news.presenter.ViewHome

class FavoritePresenter(val view: ViewHome.Favorite,
    private val dataSource: NewsDataSource): FavoriteHome.Presenter {

    fun getAll(){
        this.dataSource.getAllArticle(this)
    }

    fun saveArticle(article: Article) {
        this.dataSource.saveArticle(article)
    }
    fun deleteArticle(article: Article){
        this.dataSource.deleteArticle(article)
    }

    override fun onSuccess(articles: List<Article>) {
       this.view.showArticles(articles)
    }


}