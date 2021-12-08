package com.example.app_news.presenter

import com.example.app_news.model.Article

interface ViewHome {
    interface View{
        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressbar()
        fun showArticles(articles: List<Article>)
    }
    interface  Favorite{
        fun showArticles(articles: List<Article>)
    }
}