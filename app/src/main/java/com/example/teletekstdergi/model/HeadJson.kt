package com.example.teletekstdergi.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class HeadJson(
    @SerializedName("title")
    val title: String?,
    @SerializedName("og_description")
    val description: String?,
    @SerializedName("twitter_misc")
    val author: Author?,
    @SerializedName("og_image")
    val Image: OgImage?
)


data class Author(
    @SerializedName("Yazan:")
    val author: String?
)

@Entity
data class OgImage(
    @SerializedName("url")
    val imageUrl: String?
)