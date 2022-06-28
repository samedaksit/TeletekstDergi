package com.example.teletekstdergi.service

import com.example.teletekstdergi.model.Category
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CategoriesAPIService {

    private val BASE_URL = "https://teletekstdergi.com/wp-json/wp/v2/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CategoriesAPI::class.java)

    fun getData(): Single<List<Category>> {
        return api.getCategories()
    }
}