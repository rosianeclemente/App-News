package com.example.app_news.presenter.search

import com.example.app_news.model.NewsResponse

interface SearchHome {
    interface  Presenter{
        fun search(term: String)
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()

    }
}