package com.example.app_news.model.db

import androidx.room.*
import com.example.app_news.model.Article

@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert(article: Article) : Long

    @Query("SELECT * FROM articles")
    fun getAll(): List<Article>

    @Delete
    suspend fun delete(article: Article)

}