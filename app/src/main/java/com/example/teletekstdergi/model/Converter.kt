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
    fun authorToString(author: Author): String {
        return gson.toJson(author)
    }

    @TypeConverter
    fun stringToAuthor(data: String): Author {
        val type = object : TypeToken<Author>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun ogImageToString(imageUrl: OgImage): String {
        return gson.toJson(imageUrl)
    }

    @TypeConverter
    fun stringToOgImage(data: String): OgImage {
        val type = object : TypeToken<OgImage>() {}.type
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

    @TypeConverter
    fun categoryToString(value: List<Int>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun stringToCategory(data: String): List<Int> {
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(data, type)
    }

}