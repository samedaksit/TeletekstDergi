package com.example.teletekstdergi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ArticleTable")
data class Article(
    @PrimaryKey
    @ColumnInfo(name = "articleId")
    @SerializedName("id")
    val articleId: Int?,
    @ColumnInfo(name = "articleDate")//tarih
    @SerializedName("date")
    val articleDate: String?,
    @ColumnInfo(name = "headJson")//resim yazar başlık açıklama
    @SerializedName("yoast_head_json")
    val headJson: HeadJson?,
)
