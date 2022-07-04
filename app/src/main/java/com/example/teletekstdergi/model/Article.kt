package com.example.teletekstdergi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ArticleContent(
    val articleDate: String?,
    val articleAuthor: String?,
    val articleCategory: String?,
    val articleTitle: String,
    val articleImage: String,
    val articleContent: String
)

data class Article(
    val articleId: Int?,
    val articleDate: String?,
    val headJson: HeadJson?,
    val content: Content?
)


@Entity(tableName = "Poems")
data class Poem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val uuid: Int,
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int?,
    @ColumnInfo(name = "date")
    @SerializedName("date")
    val date: String?,
    @ColumnInfo(name = "headJson")
    @SerializedName("yoast_head_json")
    val headJson: HeadJson?,
    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: Content?
)

@Entity(tableName = "Sports")
data class Sport(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val uuid: Int,
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int?,
    @ColumnInfo(name = "date")
    @SerializedName("date")
    val date: String?,
    @ColumnInfo(name = "headJson")
    @SerializedName("yoast_head_json")
    val headJson: HeadJson?,
    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: Content?
)

@Entity(tableName = "Essays")
data class Essay(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val uuid: Int,
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int?,
    @ColumnInfo(name = "date")
    @SerializedName("date")
    val date: String?,
    @ColumnInfo(name = "headJson")
    @SerializedName("yoast_head_json")
    val headJson: HeadJson?,
    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: Content?
)

@Entity(tableName = "Reviews")
data class Review(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val uuid: Int,
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int?,
    @ColumnInfo(name = "date")
    @SerializedName("date")
    val date: String?,
    @ColumnInfo(name = "headJson")
    @SerializedName("yoast_head_json")
    val headJson: HeadJson?,
    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: Content?
)

@Entity(tableName = "Stories")
data class Story(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val uuid: Int,
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int?,
    @ColumnInfo(name = "date")
    @SerializedName("date")
    val date: String?,
    @ColumnInfo(name = "headJson")
    @SerializedName("yoast_head_json")
    val headJson: HeadJson?,
    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: Content?
)

@Entity(tableName = "ETeletekst")
data class ETeletekst(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val uuid: Int,
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int?,
    @ColumnInfo(name = "date")
    @SerializedName("date")
    val date: String?,
    @ColumnInfo(name = "headJson")
    @SerializedName("yoast_head_json")
    val headJson: HeadJson?,
    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: Content?
)

@Entity(tableName = "Psychology")
data class Psychology(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val uuid: Int,
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int?,
    @ColumnInfo(name = "date")
    @SerializedName("date")
    val date: String?,
    @ColumnInfo(name = "headJson")
    @SerializedName("yoast_head_json")
    val headJson: HeadJson?,
    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: Content?
)

@Entity(tableName = "Academies")
data class Academy(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val uuid: Int,
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int?,
    @ColumnInfo(name = "date")
    @SerializedName("date")
    val date: String?,
    @ColumnInfo(name = "headJson")
    @SerializedName("yoast_head_json")
    val headJson: HeadJson?,
    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: Content?
)