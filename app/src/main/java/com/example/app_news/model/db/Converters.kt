package com.example.app_news.model.db

import androidx.room.TypeConverter
import com.example.app_news.model.Source

class Converters {

    @TypeConverter
    fun fronSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String) : Source{
        return Source(name, name)
    }
}