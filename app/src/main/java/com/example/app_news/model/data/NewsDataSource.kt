package com.example.app_news.model.data

import com.example.app_news.network.RetrofitInstance
import com.example.app_news.presenter.news.NewsHome
import com.example.app_news.presenter.search.SearchHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsDataSource {
    fun getBreakingNews(callback: NewsHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getBreakingNews("br")
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    callback.onSuccess(newsResponse)
                }
                callback.onComplete()
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }
    fun searchNews(term: String, callback: SearchHome.Presenter){
        GlobalScope.launch(Dispatchers.Main){
            val response = RetrofitInstance.api.searchNews(term)
            if(response.isSuccessful){
                response.body()?.let{newsResponse ->
                    callback.onSuccess(newsResponse)
                }
                callback.onComplete()
            } else{
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }
}