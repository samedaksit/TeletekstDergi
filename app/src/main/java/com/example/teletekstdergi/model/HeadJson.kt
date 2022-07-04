package com.example.teletekstdergi.model

import com.google.gson.annotations.SerializedName


data class HeadJson(
    @SerializedName("title")
    val title: String?,
    @SerializedName("og_description")
    val description: String?,
    @SerializedName("og_image")
    val image: List<OgImage>?,
    @SerializedName("twitter_misc")
    val author: Author?
)


data class Author(
    @SerializedName("Yazan:")
    val author: String?
)


data class OgImage(
    @SerializedName("url")
    val imageUrl: String?
)

data class Content(
    @SerializedName("rendered")
    val rendered: String?
)