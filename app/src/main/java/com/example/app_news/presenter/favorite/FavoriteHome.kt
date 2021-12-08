package com.example.app_news.presenter.favorite

import com.example.app_news.model.Article

interface FavoriteHome {
    interface Presenter{
        fun onSuccess(articles: List<Article>)
    }


}