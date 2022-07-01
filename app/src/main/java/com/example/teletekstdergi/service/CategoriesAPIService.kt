package com.example.teletekstdergi.service


import com.example.teletekstdergi.model.Category
import com.example.teletekstdergi.model.Constants.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CategoriesAPIService {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CategoriesAPI::class.java)

    fun getCategories(): Single<List<Category>> {
        return api.getCategories()
    }
}