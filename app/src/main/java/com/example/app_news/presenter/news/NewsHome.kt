package com.example.app_news.presenter.news

import com.example.app_news.model.NewsResponse

interface NewsHome {
    interface  Presenter {
        fun requestAll()
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()
    }
}