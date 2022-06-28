package com.example.teletekstdergi.service

import com.example.teletekstdergi.model.Category
import io.reactivex.Single
import retrofit2.http.GET

interface CategoriesAPI {
    @GET("categories")
    fun getCategories(): Single<List<Category>>
}