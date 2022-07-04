package com.example.teletekstdergi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Categories")
data class Category(
    @PrimaryKey
    @SerializedName("id")
    val categoryId: Int?,
    @SerializedName("name")
    val categoryName: String?,
    var isClicked: Boolean = false
)
