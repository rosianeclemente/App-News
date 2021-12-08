package com.example.app_news.model.data

import com.example.app_news.model.Article
import com.example.app_news.model.db.ArticleDatabase

class NewsRepository(private val db: ArticleDatabase) {
    suspend fun  updateInsert(article: Article) = db.getArticleDao().updateInsert(article)

    fun getAll(): List<Article> = db.getArticleDao().getAll()

    suspend fun  delete(article: Article) = db.getArticleDao().delete(article)
}