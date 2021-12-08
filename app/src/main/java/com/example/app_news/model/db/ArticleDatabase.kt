package com.example.app_news.model.db

import android.content.Context
import androidx.room.*
import com.example.app_news.model.Article


@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract  fun getArticleDao(): ArticleDAO
    companion object {
        private var instance: ArticleDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createdDatabase(context).also{ articleDatabase ->
                 instance = articleDatabase

            }
        }

        private fun createdDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()



    }
}