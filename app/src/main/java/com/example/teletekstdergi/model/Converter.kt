package com.example.teletekstdergi.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    private val gson = Gson()

    @TypeConverter
    fun headJsonToString(ogData: HeadJson): String {
        return gson.toJson(ogData)
    }

    @TypeConverter
    fun stringToHeadJson(data: String): HeadJson {
        val type = object : TypeToken<HeadJson>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun contentToString(content: Content): String {
        return gson.toJson(content)
    }

    @TypeConverter
    fun stringToContent(data: String): Content {
        val type = object : TypeToken<Content>() {}.type
        return gson.fromJson(data, type)
    }
}